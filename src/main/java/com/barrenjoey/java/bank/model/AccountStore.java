package com.barrenjoey.java.bank.model;

import com.barrenjoey.java.bank.service.BankAccountService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountStore {
    private final Map<Integer, BankAccount> accounts = new ConcurrentHashMap<>();

    public Map<Integer, BankAccount> getAccounts() {
        return accounts;
    }
}
