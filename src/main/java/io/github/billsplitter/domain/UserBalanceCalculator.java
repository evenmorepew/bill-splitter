package io.github.billsplitter.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UserBalanceCalculator {

    Map<User, BigDecimal> calculateUserBalance(List<User> users, BigDecimal[][] expenseMatrix) {

        Map<User, BigDecimal> userBalance = new HashMap<>();

        for (int i = 0; i < users.size(); i++) {

            BigDecimal currentBalance = BigDecimal.ZERO;

            for (int j = 0; j < expenseMatrix.length; j++) {

                currentBalance = currentBalance.add(expenseMatrix[i][j]);
                currentBalance = currentBalance.subtract(expenseMatrix[j][i]);
            }

            userBalance.put(users.get(i), currentBalance);
        }

        return userBalance;
    }
}
