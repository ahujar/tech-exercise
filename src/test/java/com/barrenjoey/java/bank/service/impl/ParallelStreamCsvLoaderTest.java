package com.barrenjoey.java.bank.service.impl;

import com.barrenjoey.java.bank.model.AccountStore;
import com.barrenjoey.java.bank.model.BankAccount;
import com.barrenjoey.java.bank.model.ReportingQueue;
import com.barrenjoey.java.bank.service.Bank;
import com.barrenjoey.java.bank.service.BankAccountService;
import com.barrenjoey.java.bank.service.ReportingServer;
import org.junit.jupiter.api.Test;

import java.util.List;

class ParallelStreamCsvLoaderTest {

    AccountStore accountStore = new AccountStore();
    ReportingQueue reportingQueue = new ReportingQueue();
    BankAccountService bankAccountService = new BankAccountServiceImpl(accountStore);
    ParallelStreamCsvLoader loader = new ParallelStreamCsvLoader(bankAccountService, reportingQueue);

    Bank bankService = new BankImpl(accountStore);

    @Test
    public void load() {
        loader.load(List.of("src/main/resources/transaction-log.3.csv"));
        double balance = bankAccountService.getBalance(52222);
        BankAccount accountById = bankService.getAccountById(52222);
        assert -100.01d == balance;
        assert  5 == accountById.getAccountEntries().size();
    }

}