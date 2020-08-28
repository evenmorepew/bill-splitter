package io.github.billsplitter.domain;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.billsplitter.domain.TestdataCreator.createExpense;
import static io.github.billsplitter.domain.TestdataCreator.createUser;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;
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

    @Test
    void multiple_users_and_expenses() {

        Bill bill = new Bill();

        List<User> users = createUsers(5);

        users.forEach(bill::addUser);

        List<Expense> expenses = createExpenses(users, 20, 5.00, 50.00);

        expenses.forEach(bill::addExpense);

        BillStatus billStatus = bill.getCurrentStatus();

        Collection<Payment> payments = billStatus.getPayments();

        MoneyAmount sum = expenses.stream()
                .map(Expense::getAmount)
                .reduce(MoneyAmount::add)
                .orElse(MoneyAmount.ZERO);

        Map<User, MoneyAmount> userToAmountPayed = expenses.stream()
                .collect(groupingBy(Expense::getPayer,
                        reducing(MoneyAmount.ZERO, Expense::getAmount, MoneyAmount::add)));

        payments.forEach(p -> pay(p, userToAmountPayed));

        MoneyAmount sumAfterPayments = userToAmountPayed.values()
                .stream()
                .reduce(MoneyAmount::add)
                .orElse(MoneyAmount.ZERO);

        assertThat(sum, is(sumAfterPayments));
    }

    private void pay(Payment payment, Map<User, MoneyAmount> userToAmountPayed) {
        userToAmountPayed.compute(payment.getRecipient(), (k, v) -> v == null ? MoneyAmount.ZERO : v.subtract(payment.getAmount()));
        userToAmountPayed.compute(payment.getIssuer(), (k, v) -> v == null ? payment.getAmount() : v.add(payment.getAmount()));
    }

    private List<User> createUsers(int numberOfUsers) {
        return IntStream.range(0, numberOfUsers)
                .mapToObj(i -> createUser(UUID.randomUUID().toString()))
                .collect(Collectors.toList());
    }

    private List<Expense> createExpenses(List<User> users, int numberOfExpenses, double amountFrom, double amountTo) {
        return IntStream.range(0, numberOfExpenses)
                .mapToObj(i -> createExpense(
                        users.get(i % users.size()),
                        String.format("Expense %d", i),
                        ThreadLocalRandom.current().nextDouble(amountFrom, amountTo),
                        users))
                .collect(Collectors.toList());
    }
}
