package io.github.billsplitter.domain;

import java.util.List;

class ExpenseMatrixCreator {

    ExpenseMatrix createMatrix(List<User> users, List<Expense> expenses) {

        int size = users.size();
        ExpenseMatrix expenseMatrix = new ExpenseMatrix(size);

        for (Expense expense : expenses) {

            int numberOfInvolvedUsers = expense.getInvolvedUsers().size();

            MoneyAmount amount = expense.getAmount();
            MoneyAmount amountPerUser = amount.divide(numberOfInvolvedUsers);

            MoneyAmount rest = amount.subtract(amountPerUser.multiply(numberOfInvolvedUsers));

            for (User involvedUser : expense.getInvolvedUsers()) {

                int recipientIndex = users.indexOf(expense.getPayer());
                int payerIndex = users.indexOf(involvedUser);

                if (recipientIndex == payerIndex) {
                    continue;
                }

                MoneyAmount amountPerUserWithRest = amountPerUser;
                if (rest.isGreaterZero()) {
                    amountPerUserWithRest = amountPerUser.add(MoneyAmount.ONE_CENT);
                    rest = rest.subtract(MoneyAmount.ONE_CENT);
                }

                expenseMatrix.addAmount(amountPerUserWithRest, recipientIndex, payerIndex);
            }
        }

        return expenseMatrix;
    }
}
