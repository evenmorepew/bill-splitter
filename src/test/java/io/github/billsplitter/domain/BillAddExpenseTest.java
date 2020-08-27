package io.github.billsplitter.domain;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static io.github.billsplitter.domain.TestdataCreator.createExpense;
import static io.github.billsplitter.domain.TestdataCreator.createUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsIterableContaining.hasItem;

public class BillAddExpenseTest {

    @Test
    void user2_pays_user1() {

        Bill bill = new Bill();

        User user1 = createUser("123");
        bill.addUser(user1);

        User user2 = createUser("456");
        bill.addUser(user2);

        Expense expense = createExpense(user1, "321", 2.00, List.of(user1, user2));
        bill.addExpense(expense);

        BillStatus billStatus = bill.getCurrentStatus();
        Collection<Payment> payments = billStatus.getPayments();

        Payment expectedPaymentUser2 = Payment
                .builder()
                .issuer(user2)
                .recipient(user1)
                .amount(MoneyAmount.of(1.00))
                .build();
        assertThat(payments, hasItem(expectedPaymentUser2));
    }

    @Test
    void user3_pays_user2_and_user1_same_amount() {

        Bill bill = new Bill();

        User user1 = createUser("123");
        bill.addUser(user1);

        User user2 = createUser("456");
        bill.addUser(user2);

        User user3 = createUser("789");
        bill.addUser(user3);

        Expense expense1 = createExpense(user1, "LI-1", 3.00, List.of(user1, user2, user3));
        bill.addExpense(expense1);

        Expense expense2 = createExpense(user2, "LI-2", 3.00, List.of(user1, user2, user3));
        bill.addExpense(expense2);

        BillStatus billStatus = bill.getCurrentStatus();

        Collection<Payment> payments = billStatus.getPayments();

        Payment expectedPaymentUser3ToUser1 = Payment
                .builder()
                .issuer(user3)
                .recipient(user1)
                .amount(MoneyAmount.of(1.00))
                .build();

        Payment expectedPaymentUser3ToUser2 = Payment
                .builder()
                .issuer(user3)
                .recipient(user2)
                .amount(MoneyAmount.of(1.00))
                .build();

        assertThat(payments, containsInAnyOrder(expectedPaymentUser3ToUser1, expectedPaymentUser3ToUser2));
    }

    @Test
    void user3_pays_user2_and_user1_different_amount_1() {

        Bill bill = new Bill();

        User user1 = createUser("123");
        bill.addUser(user1);

        User user2 = createUser("456");
        bill.addUser(user2);

        User user3 = createUser("789");
        bill.addUser(user3);

        Expense expense1 = createExpense(user1, "LI-1", 10.00, List.of(user1, user2, user3));
        bill.addExpense(expense1);

        Expense expense2 = createExpense(user2, "LI-2", 14.00, List.of(user1, user2, user3));
        bill.addExpense(expense2);

        BillStatus billStatus = bill.getCurrentStatus();

        Collection<Payment> payments = billStatus.getPayments();

        Payment expectedPaymentUser3ToUser1 = Payment
                .builder()
                .issuer(user3)
                .recipient(user1)
                .amount(MoneyAmount.of(2.00))
                .build();

        Payment expectedPaymentUser3ToUser2 = Payment
                .builder()
                .issuer(user3)
                .recipient(user2)
                .amount(MoneyAmount.of(6.00))
                .build();

        assertThat(payments, containsInAnyOrder(expectedPaymentUser3ToUser1, expectedPaymentUser3ToUser2));
    }

    @Test
    void user3_pays_user2_and_user1_different_amount_2() {

        Bill bill = new Bill();

        User user1 = createUser("123");
        bill.addUser(user1);

        User user2 = createUser("456");
        bill.addUser(user2);

        User user3 = createUser("789");
        bill.addUser(user3);

        Expense expense1 = createExpense(user1, "LI-1", 26.96, List.of(user1, user2, user3));
        bill.addExpense(expense1);

        Expense expense2 = createExpense(user2, "LI-2", 15.59, List.of(user1, user2, user3));
        bill.addExpense(expense2);

        BillStatus billStatus = bill.getCurrentStatus();

        Collection<Payment> payments = billStatus.getPayments();

        Payment expectedPaymentUser3ToUser1 = Payment
                .builder()
                .issuer(user3)
                .recipient(user1)
                .amount(MoneyAmount.of(12.78))
                .build();

        Payment expectedPaymentUser3ToUser2 = Payment
                .builder()
                .issuer(user3)
                .recipient(user2)
                .amount(MoneyAmount.of(1.41))
                .build();

        assertThat(payments, containsInAnyOrder(expectedPaymentUser3ToUser1, expectedPaymentUser3ToUser2));
    }

    @Test
    void user3_pays_user2_and_user1_different_amount_not_all_expenses_involve_all_users_1() {

        Bill bill = new Bill();

        User user1 = createUser("123");
        bill.addUser(user1);

        User user2 = createUser("456");
        bill.addUser(user2);

        User user3 = createUser("789");
        bill.addUser(user3);

        Expense expense1 = createExpense(user1, "LI-1", 10.00, List.of(user1, user2));
        bill.addExpense(expense1);

        Expense expense2 = createExpense(user2, "LI-2", 15.00, List.of(user1, user2, user3));
        bill.addExpense(expense2);

        BillStatus billStatus = bill.getCurrentStatus();

        Collection<Payment> payments = billStatus.getPayments();

        Payment expectedPaymentUser3ToUser2 = Payment
                .builder()
                .issuer(user3)
                .recipient(user2)
                .amount(MoneyAmount.of(5.00))
                .build();

        assertThat(payments, containsInAnyOrder(expectedPaymentUser3ToUser2));
    }

    @Test
    void user3_pays_user2_and_user1_different_amount_not_all_expenses_involve_all_users_2() {

        Bill bill = new Bill();

        User user1 = createUser("123");
        bill.addUser(user1);

        User user2 = createUser("456");
        bill.addUser(user2);

        User user3 = createUser("789");
        bill.addUser(user3);

        Expense expense1 = createExpense(user1, "LI-1", 12.33, List.of(user1, user2));
        bill.addExpense(expense1);

        Expense expense2 = createExpense(user2, "LI-2", 17.17, List.of(user1, user2, user3));
        bill.addExpense(expense2);

        BillStatus billStatus = bill.getCurrentStatus();

        Collection<Payment> payments = billStatus.getPayments();

        Payment expectedPaymentUser3ToUser1 = Payment
                .builder()
                .issuer(user3)
                .recipient(user1)
                .amount(MoneyAmount.of(0.44))
                .build();

        Payment expectedPaymentUser3ToUser2 = Payment
                .builder()
                .issuer(user3)
                .recipient(user2)
                .amount(MoneyAmount.of(5.28))
                .build();

        assertThat(payments, containsInAnyOrder(expectedPaymentUser3ToUser1, expectedPaymentUser3ToUser2));

        MoneyAmount total = expense1.getAmount().add(expense2.getAmount());
        MoneyAmount user1Expenditures = expense1.getAmount().subtract(expectedPaymentUser3ToUser1.getAmount());
        MoneyAmount user2Expenditures = expense2.getAmount().subtract(expectedPaymentUser3ToUser2.getAmount());
        MoneyAmount user3Expenditures = expectedPaymentUser3ToUser1.getAmount().add(expectedPaymentUser3ToUser2.getAmount());

        assertThat(total, is(user1Expenditures.add(user2Expenditures).add(user3Expenditures)));
    }
}
