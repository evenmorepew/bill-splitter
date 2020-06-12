package io.github.billsplitter.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

class ExpenseMatrixCreator {

    BigDecimal[][] createMatrix(List<User> users, List<Expense> expenses) {

        int size = users.size();
        BigDecimal[][] recipientToPayer = new BigDecimal[size][size];

        initialiseArray(recipientToPayer);

        for (Expense expense : expenses) {

            BigDecimal amount = expense.getAmount();
            BigDecimal amountPerUser = amount.divide(BigDecimal.valueOf(expense.getInvolvedUsers().size()), RoundingMode.HALF_UP);

            for (User involvedUser : expense.getInvolvedUsers()) {

                int recipientIndex = users.indexOf(expense.getPayer());
                int payerIndex = users.indexOf(involvedUser);

                if (recipientIndex == payerIndex) {
                    continue;
                }

                BigDecimal currentValue = recipientToPayer[recipientIndex][payerIndex];
                recipientToPayer[recipientIndex][payerIndex] = currentValue.add(amountPerUser);
            }
        }

        return recipientToPayer;
    }

    private void initialiseArray(BigDecimal[][] recipientToPayer) {
        for (BigDecimal[] bigDecimals : recipientToPayer) {
            Arrays.fill(bigDecimals, BigDecimal.ZERO);
        }
    }
}
