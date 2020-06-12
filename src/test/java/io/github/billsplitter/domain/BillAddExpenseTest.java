package io.github.billsplitter.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static io.github.billsplitter.domain.Identifier.fromString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.IsIterableContaining.hasItem;

public class BillAddExpenseTest {

    @Test
    void user2_pays_user1() {

        Bill bill = new Bill();

        User user1 = createUser("123");
        bill.addUser(user1);

        User user2 = createUser("456");
        bill.addUser(user2);

        Expense expense = createExpense(user1, "321", 2L, List.of(user1, user2));
        bill.addExpense(expense);

        BillStatus billStatus = bill.getCurrentStatus();
        Collection<Payment> payments = billStatus.getPayments();

        Payment expectedPaymentUser2 = Payment
                .builder()
                .issuer(user2)
                .recipient(user1)
                .amount(BigDecimal.valueOf(1L))
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

        Expense expense1 = createExpense(user1, "LI-1", 3L, List.of(user1, user2, user3));
        bill.addExpense(expense1);

        Expense expense2 = createExpense(user2, "LI-2", 3L, List.of(user1, user2, user3));
        bill.addExpense(expense2);

        BillStatus billStatus = bill.getCurrentStatus();

        Collection<Payment> payments = billStatus.getPayments();

        Payment expectedPaymentUser3ToUser1 = Payment
                .builder()
                .issuer(user3)
                .recipient(user1)
                .amount(BigDecimal.ONE)
                .build();

        Payment expectedPaymentUser3ToUser2 = Payment
                .builder()
                .issuer(user3)
                .recipient(user2)
                .amount(BigDecimal.ONE)
                .build();

        assertThat(payments, containsInAnyOrder(expectedPaymentUser3ToUser1, expectedPaymentUser3ToUser2));
    }

    @Test
    void user3_pays_user2_and_user1_different_amount() {

        Bill bill = new Bill();

        User user1 = createUser("123");
        bill.addUser(user1);

        User user2 = createUser("456");
        bill.addUser(user2);

        User user3 = createUser("789");
        bill.addUser(user3);

        Expense expense1 = createExpense(user1, "LI-1", 10L, List.of(user1, user2, user3));
        bill.addExpense(expense1);

        Expense expense2 = createExpense(user2, "LI-2", 14L, List.of(user1, user2, user3));
        bill.addExpense(expense2);

        BillStatus billStatus = bill.getCurrentStatus();

        Collection<Payment> payments = billStatus.getPayments();

        Payment expectedPaymentUser3ToUser1 = Payment
                .builder()
                .issuer(user3)
                .recipient(user1)
                .amount(BigDecimal.valueOf(2L))
                .build();

        Payment expectedPaymentUser3ToUser2 = Payment
                .builder()
                .issuer(user3)
                .recipient(user2)
                .amount(BigDecimal.valueOf(6))
                .build();

        assertThat(payments, containsInAnyOrder(expectedPaymentUser3ToUser1, expectedPaymentUser3ToUser2));
    }

    @Test
    void user3_pays_user2_and_user1_different_amount_not_all_expenses_involve_all_users() {

        Bill bill = new Bill();

        User user1 = createUser("123");
        bill.addUser(user1);

        User user2 = createUser("456");
        bill.addUser(user2);

        User user3 = createUser("789");
        bill.addUser(user3);

        Expense expense1 = createExpense(user1, "LI-1", 10L, List.of(user1, user2));
        bill.addExpense(expense1);

        Expense expense2 = createExpense(user2, "LI-2", 15L, List.of(user1, user2, user3));
        bill.addExpense(expense2);

        BillStatus billStatus = bill.getCurrentStatus();

        Collection<Payment> payments = billStatus.getPayments();

        Payment expectedPaymentUser3ToUser2 = Payment
                .builder()
                .issuer(user3)
                .recipient(user2)
                .amount(BigDecimal.valueOf(5L))
                .build();

        assertThat(payments, containsInAnyOrder(expectedPaymentUser3ToUser2));
    }

    private User createUser(String s) {
        return User.builder()
                .identifier(fromString(s))
                .build();
    }

    private Expense createExpense(User payer, String id, long amount, List<User> involvedUsers) {
        return Expense.builder()
                .identifier(fromString(id))
                .amount(BigDecimal.valueOf(amount))
                .payer(payer)
                .involvedUsers(involvedUsers)
                .build();
    }
}
