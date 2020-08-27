package io.github.billsplitter.domain;

import java.util.List;

import static io.github.billsplitter.domain.Identifier.fromString;

public class TestdataCreator {

    static Expense createExpense(User payer, String id, double amount, List<User> involvedUsers) {
        return Expense.builder()
                .identifier(fromString(id))
                .amount(MoneyAmount.of(amount))
                .payer(payer)
                .involvedUsers(involvedUsers)
                .build();
    }

    static User createUser(String s) {
        return User.builder()
                .identifier(fromString(s))
                .build();
    }
}
