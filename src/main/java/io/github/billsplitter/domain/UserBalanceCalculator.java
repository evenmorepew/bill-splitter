package io.github.billsplitter.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UserBalanceCalculator {

    Map<User, MoneyAmount> calculateUserBalance(List<User> users, ExpenseMatrix expenseMatrix) {

        Map<User, MoneyAmount> userBalance = new HashMap<>();

        for (int i = 0; i < users.size(); i++) {

            MoneyAmount currentBalance = MoneyAmount.of(0.00);

            for (int j = 0; j < expenseMatrix.length(); j++) {

                currentBalance = currentBalance.add(expenseMatrix.get(i, j));
                currentBalance = currentBalance.subtract(expenseMatrix.get(j, i));
            }

            userBalance.put(users.get(i), currentBalance);
        }

        return userBalance;
    }
}
