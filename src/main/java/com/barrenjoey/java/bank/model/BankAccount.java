package com.barrenjoey.java.bank.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BankAccount {
    private final int accountId;
    private final AtomicReference<Double> balance;
    private final List<AccountEntry> accountEntries;

    public BankAccount(AccountEntry accountEntry) {
        this.accountId = accountEntry.accountId();
        this.balance = new AtomicReference<>(accountEntry.action().getSign()*accountEntry.amount());
        this.accountEntries = Collections.synchronizedList(new ArrayList<>());
        accountEntries.add(accountEntry);
    }

    public int getAccountId() {
        return accountId;
    }

    public AtomicReference<Double> getBalance() {
        return balance;
    }

    public List<AccountEntry> getAccountEntries() {
        return accountEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount that)) return false;

        if (accountId != that.accountId) return false;
        if (!balance.get().equals(that.balance.get())) return false;
        return accountEntries.equals(that.accountEntries);
    }

    @Override
    public int hashCode() {
        int result = accountId;
        result = 31 * result + balance.hashCode();
        result = 31 * result + accountEntries.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", accountEntries=" + accountEntries +
                '}';
    }
}
