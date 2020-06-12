package io.github.billsplitter.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static io.github.billsplitter.domain.Identifier.fromString;
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
        Expense expense1 = createExpense(user1, "LI-1", 10L, List.of(user1, user2));
        expenses.add(expense1);
        Expense expense2 = createExpense(user2, "LI-2", 15L, List.of(user1, user2, user3));
        expenses.add(expense2);
        Expense expense3 = createExpense(user3, "LI-3", 20L, List.of(user2, user3));
        expenses.add(expense3);

        ExpenseMatrixCreator expenseMatrixCreator = new ExpenseMatrixCreator();
        BigDecimal[][] matrix = expenseMatrixCreator.createMatrix(users, expenses);

        assertThat(matrix[0][0], is(BigDecimal.ZERO));
        assertThat(matrix[0][1], is(BigDecimal.valueOf(5)));
        assertThat(matrix[0][2], is(BigDecimal.ZERO));
        assertThat(matrix[1][0], is(BigDecimal.valueOf(5)));
        assertThat(matrix[1][1], is(BigDecimal.ZERO));
        assertThat(matrix[1][2], is(BigDecimal.valueOf(5)));
        assertThat(matrix[2][0], is(BigDecimal.ZERO));
        assertThat(matrix[2][1], is(BigDecimal.valueOf(10)));
        assertThat(matrix[2][2], is(BigDecimal.ZERO));
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