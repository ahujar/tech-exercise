package com.barrenjoey.java.bank;

public class BankAccountImpl implements BankAccount {

    private final int accountId;
    private double balance;

    public BankAccountImpl(int accountId, double startingBalance) {
        this.accountId = accountId;
        this.balance = startingBalance;
    }

    private double apply(double deltaAmount) {
        balance = balance + deltaAmount;
        return balance;
    }

    @Override
    public double deposit(double depositAmount) {
        if(depositAmount < 0) throw new IllegalArgumentException("depositAmount must be positive");
        return apply(depositAmount);
    }

    @Override
    public double withdraw(double withdrawalAmount) {
        if(withdrawalAmount < 0) throw new IllegalArgumentException("withdrawalAmount must be positive");
        return apply(withdrawalAmount);
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public AcccountEntry[] entries() {
        //TODO you need to implement this method, the programmer didn't make it here before their holiday...
        return new AcccountEntry[0];
    }

    @Override
    public String toString() {
        return "BankAccountImpl{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                '}';
    }
}
