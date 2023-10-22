package com.barrenjoey.java.bank.model;

import java.time.Instant;

/**
 * Incomplete AccountEntry class...
 */
public record AccountEntry(int accountId, Action action, double amount, Instant transactionTime, double effectiveBalance) {
    @Override
    public String toString() {
        return "AccountEntry{" +
                "accountId=" + accountId +
                ", action=" + action +
                ", amount=" + amount +
                ", transactionTime=" + transactionTime +
                ", effectiveBalance=" + effectiveBalance +
                '}';
    }
}
