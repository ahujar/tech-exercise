package com.barrenjoey.java.bank.service;

import com.barrenjoey.java.bank.model.AccountEntry;
import com.barrenjoey.java.bank.model.Action;

import java.time.Instant;

public interface BankAccountService {

    /**
     * Increase the balance of the account by depositAmount
     * @param depositAmount
     * @return the updated balance
     */
    double deposit(int accountId, double depositAmount);

    /**
     * Decrease the balance of the account by withdrawalAmount
     * @param withdrawalAmount
     * @return the updated balance
     */
    double withdraw(int accountId, double withdrawalAmount);

    /**
     * Get the current balance of the account
     * @return the balance
     */
    double getBalance(int accountId);

    /**
     * Return the individual account entries in the order they were applied
     * @return the entries
     */
    AccountEntry[] entries(int accountId);

    /**
     * Applies a transaction entry to an account
     * @param accountId  accountId for the transaction data needs to be applied
     * @param amount    amount to be deposited/withdrawn
     * @param action    can be Deposit/Withdraw
     * @param transactionTime time of applied transaction
     * @return
     */
    double apply(int accountId, double amount, Action action, Instant transactionTime);
}
