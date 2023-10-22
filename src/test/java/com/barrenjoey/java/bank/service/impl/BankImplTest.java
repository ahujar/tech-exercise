package com.barrenjoey.java.bank.service.impl;

import com.barrenjoey.java.bank.model.AccountStore;
import com.barrenjoey.java.bank.model.BankAccount;
import com.barrenjoey.java.bank.service.Bank;
import com.barrenjoey.java.bank.service.BankAccountService;
import org.junit.jupiter.api.Test;

class BankImplTest {

    private final AccountStore accountStore = new AccountStore();
    private final Bank bank = new BankImpl(accountStore);
    private final BankAccountService bankAccountService = new BankAccountServiceImpl(accountStore);

    @Test
    void getAccountById() {
        BankAccount intial = bank.getAccountById(10);
        assert null == intial;
        bankAccountService.deposit(10, 100);
        intial = bank.getAccountById(10);
        assert intial.getAccountEntries().get(0).effectiveBalance() == 100d;
    }
}