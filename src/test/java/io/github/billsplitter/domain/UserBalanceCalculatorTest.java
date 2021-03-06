package io.github.billsplitter.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.github.billsplitter.domain.TestdataCreator.createExpense;
import static io.github.billsplitter.domain.TestdataCreator.createUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class UserBalanceCalculatorTest {

    @Test
    void name() {
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

        MoneyAmount balanceUser1 = userBalance.get(user1);
        assertThat(balanceUser1, is(MoneyAmount.of(0.00)));

        MoneyAmount balanceUser2 = userBalance.get(user2);
        assertThat(balanceUser2, is(MoneyAmount.of(-5.00)));

        MoneyAmount balanceUser3 = userBalance.get(user3);
        assertThat(balanceUser3, is(MoneyAmount.of(5.00)));
    }
}