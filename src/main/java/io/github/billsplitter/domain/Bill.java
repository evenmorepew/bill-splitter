package io.github.billsplitter.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class Bill {

    private final List<User> users;
    private final List<Expense> expenses;

    private BillStatus currentStatus;

    Bill() {
        this.users = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    void addUser(User user) {
        this.users.add(user);
    }

    void removeUser(User user) {
        this.users.remove(user);
    }

    public int getNumberOfUsers() {
        return users.size();
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);

        reCalc();
    }

    private void reCalc() {

        ExpenseMatrixCreator expenseMatrixCreator = new ExpenseMatrixCreator();
        ExpenseMatrix matrix = expenseMatrixCreator.createMatrix(users, expenses);

        UserBalanceCalculator userBalanceCalculator = new UserBalanceCalculator();
        Map<User, MoneyAmount> userBalance = userBalanceCalculator.calculateUserBalance(users, matrix);

        PaymentCalculator paymentCalculator = new PaymentCalculator();
        Collection<Payment> payments = paymentCalculator.calc(userBalance);

        currentStatus = BillStatus.of(payments);
    }

    public BillStatus getCurrentStatus() {
        return currentStatus;
    }
}
