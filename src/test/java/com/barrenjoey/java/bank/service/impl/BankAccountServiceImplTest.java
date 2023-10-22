package com.barrenjoey.java.bank.service.impl;

import com.barrenjoey.java.bank.model.AccountEntry;
import com.barrenjoey.java.bank.model.AccountStore;
import com.barrenjoey.java.bank.model.Action;
import com.barrenjoey.java.bank.service.BankAccountService;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankAccountServiceImplTest {

    private final AccountStore accountStore = new AccountStore();
    private final BankAccountService bankAccountService = new BankAccountServiceImpl(accountStore);

    @Test
    void apply() {
        double balance = bankAccountService.apply(1, 10, Action.Withdraw, Instant.now());
        assertEquals(-10d, balance);
        balance = bankAccountService.apply(1, 10, Action.Deposit, Instant.now());
        assertEquals(0d, balance);
    }

    @Test
    void deposit() {
        double deposit = bankAccountService.deposit(2, 10);
        assertEquals(10d, deposit);
    }

    @Test
    void withdraw() {
        double withdraw = bankAccountService.withdraw(3, 10);
        assertEquals(-10d, withdraw);
    }

    @Test
    void getBalance() {
        double deposit = bankAccountService.deposit(4, 10);
        assertEquals(deposit, bankAccountService.getBalance(4));
    }

    @Test
    void entries() {
        double deposit = bankAccountService.deposit(5, 10);
        AccountEntry[] entries = bankAccountService.entries(5);
        assertEquals(1,entries.length);
        assert entries[0].effectiveBalance() == deposit;
    }
}