package io.github.billsplitter.domain;

import java.util.Arrays;

public class ExpenseMatrix {

    private final MoneyAmount[][] expenseMatrix;

    public ExpenseMatrix(int size) {
        MoneyAmount[][] recipientToPayer = new MoneyAmount[size][size];
        initialiseArray(recipientToPayer);

        this.expenseMatrix = recipientToPayer;
    }

    private void initialiseArray(MoneyAmount[][] recipientToPayer) {
        for (MoneyAmount[] moneyAmounts : recipientToPayer) {
            Arrays.fill(moneyAmounts, MoneyAmount.of(0.00));
        }
    }

    public void addAmount(MoneyAmount amount, int recipientIndex, int payerIndex) {
        MoneyAmount currentValue = expenseMatrix[recipientIndex][payerIndex];
        expenseMatrix[recipientIndex][payerIndex] = currentValue.add(amount);
    }

    public MoneyAmount get(int i, int j) {
        return expenseMatrix[i][j];
    }

    public int length() {
        return expenseMatrix.length;
    }
}
