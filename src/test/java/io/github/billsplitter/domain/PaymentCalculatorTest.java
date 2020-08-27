package io.github.billsplitter.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static io.github.billsplitter.domain.TestdataCreator.createExpense;
import static io.github.billsplitter.domain.TestdataCreator.createUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItem;

class PaymentCalculatorTest {

    @Test
    void calc() {
        List<User> users = new ArrayList<>();
        User user1 = createUser("123");
        users.add(user1);
        User user2 = createUser("456");
        users.add(user2);
        User user3 = createUser("789");
        users.add(user3);

        List<Expense> expenses = new ArrayList<>();
        Expense expense1 = createExpense(user1, "LI-1", 10.00, List.of(user1, user2));
        expenses.add(expense1);
        Expense expense2 = createExpense(user2, "LI-2", 15.00, List.of(user1, user2, user3));
        expenses.add(expense2);
        Expense expense3 = createExpense(user3, "LI-3", 20.00, List.of(user2, user3));
        expenses.add(expense3);

        ExpenseMatrixCreator expenseMatrixCreator = new ExpenseMatrixCreator();
        ExpenseMatrix matrix = expenseMatrixCreator.createMatrix(users, expenses);

        UserBalanceCalculator userBalanceCalculator = new UserBalanceCalculator();
        Map<User, MoneyAmount> userBalance = userBalanceCalculator.calculateUserBalance(users, matrix);

        PaymentCalculator paymentCalculator = new PaymentCalculator();
        Collection<Payment> calc = paymentCalculator.calc(userBalance);

        Payment user2ToUser3 = Payment.builder()
                .issuer(user2)
                .recipient(user3)
                .amount(MoneyAmount.of(5.00))
                .build();
        assertThat(calc, hasItem(user2ToUser3));
    }
}