package com.barrenjoey.java.bank.service.impl;

import com.barrenjoey.java.bank.model.AccountEntry;
import com.barrenjoey.java.bank.model.AccountStore;
import com.barrenjoey.java.bank.model.Action;
import com.barrenjoey.java.bank.model.BankAccount;
import com.barrenjoey.java.bank.service.BankAccountService;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankAccountServiceImpl implements BankAccountService {

    public final static Logger logger = Logger.getLogger(BankAccountServiceImpl.class.getName());
    private final AccountStore accountStore;

    public BankAccountServiceImpl(AccountStore accountStore) {
        this.accountStore = accountStore;
    }

    @Override
    public double apply(int accountId, double amount, Action action, Instant transactionTime) {
        return accountStore.getAccounts().compute(accountId, (id, bankAccount) -> {
            if (bankAccount == null) {
                AccountEntry entry = new AccountEntry(accountId, action, amount, transactionTime, action.getSign() * amount);
                return new BankAccount(entry);
            } else {
                Double effectiveBalance = bankAccount.getBalance().accumulateAndGet(action.getSign() * amount, Double::sum);
                AccountEntry entry = new AccountEntry(accountId, action, amount, transactionTime, effectiveBalance);
                bankAccount.getAccountEntries().add(entry);
                return bankAccount;
            }
        }).getBalance().get();
    }

    @Override
    public double deposit(int accountId, double depositAmount) {
        if (depositAmount < 0) throw new IllegalArgumentException("depositAmount must be positive");
        return apply(accountId, depositAmount, Action.Deposit, Instant.now());
    }

    @Override
    public double withdraw(int accountId, double withdrawalAmount) {
        if (withdrawalAmount < 0) throw new IllegalArgumentException("withdrawalAmount must be positive");
        return apply(accountId, withdrawalAmount, Action.Withdraw, Instant.now());
    }

    @Override
    public double getBalance(int accountId) {
        BankAccount bankAccount = accountStore.getAccounts().get(accountId);
        if (null != bankAccount) {
            return bankAccount.getBalance().get();
        }
        logger.log(Level.SEVERE, "Account with account number:" + accountId + " does not exist");
        throw new NoSuchElementException("Account with account number:" + accountId + " does not exist");
    }

    @Override
    public AccountEntry[] entries(int accountId) {
        BankAccount bankAccount = accountStore.getAccounts().get(accountId);
        if (null != bankAccount) {
            return bankAccount.getAccountEntries().toArray(new AccountEntry[0]);
        }
        logger.log(Level.SEVERE, "Account with account number:" + accountId + " does not exist");
        throw new NoSuchElementException("Account with account number:" + accountId + " does not exist");
    }
}
