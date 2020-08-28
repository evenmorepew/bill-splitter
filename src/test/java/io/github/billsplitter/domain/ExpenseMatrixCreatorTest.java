package io.github.billsplitter.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.github.billsplitter.domain.TestdataCreator.createExpense;
import static io.github.billsplitter.domain.TestdataCreator.createUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ExpenseMatrixCreatorTest {

    @Test
    void test_matrix_building() {

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

        assertThat(matrix.get(0, 0), is(MoneyAmount.of(0.00)));
        assertThat(matrix.get(0, 1), is(MoneyAmount.of(5.00)));
        assertThat(matrix.get(0, 2), is(MoneyAmount.of(0.00)));
        assertThat(matrix.get(1, 0), is(MoneyAmount.of(5.00)));
        assertThat(matrix.get(1, 1), is(MoneyAmount.of(0.00)));
        assertThat(matrix.get(1, 2), is(MoneyAmount.of(5.00)));
        assertThat(matrix.get(2, 0), is(MoneyAmount.of(0.00)));
        assertThat(matrix.get(2, 1), is(MoneyAmount.of(10.00)));
        assertThat(matrix.get(2, 2), is(MoneyAmount.of(0.00)));
    }

    /**
     * Expects that the rest is given to the first user in the list of users.
     */
    @Test
    void test_matrix_building_with_division_rest() {

        List<User> users = new ArrayList<>();
        User user1 = createUser("123");
        users.add(user1);
        User user2 = createUser("456");
        users.add(user2);
        User user3 = createUser("789");
        users.add(user3);

        List<Expense> expenses = new ArrayList<>();
        Expense expense1 = createExpense(user1, "LI-1", 10.00, List.of(user1, user2, user3));
        expenses.add(expense1);

        Expense expense2 = createExpense(user2, "LI-2", 14.00, List.of(user1, user2, user3));
        expenses.add(expense2);

        ExpenseMatrixCreator expenseMatrixCreator = new ExpenseMatrixCreator();
        ExpenseMatrix matrix = expenseMatrixCreator.createMatrix(users, expenses);

        assertThat(matrix.get(0, 0), is(MoneyAmount.of(0.00)));
        assertThat(matrix.get(0, 1), is(MoneyAmount.of(3.34)));
        assertThat(matrix.get(0, 2), is(MoneyAmount.of(3.33)));
        assertThat(matrix.get(1, 0), is(MoneyAmount.of(4.67)));
        assertThat(matrix.get(1, 1), is(MoneyAmount.of(0.00)));
        assertThat(matrix.get(1, 2), is(MoneyAmount.of(4.67)));
        assertThat(matrix.get(2, 0), is(MoneyAmount.of(0.00)));
        assertThat(matrix.get(2, 1), is(MoneyAmount.of(0.00)));
        assertThat(matrix.get(2, 2), is(MoneyAmount.of(0.00)));
    }
}