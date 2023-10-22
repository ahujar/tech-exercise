package com.barrenjoey.java.bank.service.impl;

import com.barrenjoey.java.bank.model.AccountStore;
import com.barrenjoey.java.bank.model.BankAccount;
import com.barrenjoey.java.bank.service.Bank;
import com.barrenjoey.java.bank.service.BankAccountService;

public class BankImpl implements Bank {

    private final AccountStore accountStore;

    public BankImpl(AccountStore accountStore) {
        this.accountStore = accountStore;
    }

    @Override
    public BankAccount getAccountById(int accountId) {
        return accountStore.getAccounts().get(accountId);
    }
}
