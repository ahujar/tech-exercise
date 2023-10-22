package com.barrenjoey.java.bank.model;

import java.util.NoSuchElementException;

public enum Action {
    Deposit(1), Withdraw(-1);
    private final int sign;

    Action(int sign) {
        this.sign = sign;
    }

    public static Action fromString(String s) {
        return switch (s.toLowerCase()) {
            case "deposit" -> Deposit;
            case "withdraw" -> Withdraw;
            default -> throw new NoSuchElementException(s + " is not a valid action!!");
        };
    }

    public int getSign() {
        return sign;
    }
}
