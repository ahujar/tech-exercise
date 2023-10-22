package com.barrenjoey.java.bank.service;

import com.barrenjoey.java.bank.model.BankAccount;

public interface Bank {
    /**
     * Clients should be able to use the bank interface to get an account.
     * It's important that an account is in a consistent state. i.e. it's entries correspond to it's balance
     *
     * @param accountId
     * @return
     */
    BankAccount getAccountById(int accountId);


}
