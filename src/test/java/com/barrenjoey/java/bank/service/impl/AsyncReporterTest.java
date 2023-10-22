package com.barrenjoey.java.bank.service.impl;

import com.barrenjoey.java.bank.model.AccountEntry;
import com.barrenjoey.java.bank.model.Action;
import com.barrenjoey.java.bank.model.ReportingQueue;
import com.barrenjoey.java.bank.service.ReportingServer;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AsyncReporterTest {

    private final ReportingServer reportingServer = new LegacyReportingServer();
    private final ReportingQueue reportingQueue = new ReportingQueue();
    private final AsyncReporter asyncReporter = new AsyncReporter(reportingServer, reportingQueue);

    @Test
    void report() {
        AccountEntry entry = getAccountEntry(1, 100, Action.Deposit, 100);
        IntStream.range(0, 20).forEach(i -> reportingQueue.getReportingQueue().offer(entry));
        List<CompletableFuture<Void>> report = asyncReporter.report();
        asyncReporter.gracefulShutdown();
        report.forEach(CompletableFuture::join);
        assertEquals(0, reportingQueue.getReportingQueue().size());
    }

    private AccountEntry getAccountEntry(int accountId, int amount, Action action, int effectiveBalance) {
        return new AccountEntry(accountId, action, amount, Instant.now(), effectiveBalance);
    }

    @Test
    void gracefulShutdown() {
        List<CompletableFuture<Void>> report = asyncReporter.report();
        asyncReporter.gracefulShutdown();
        report.forEach(CompletableFuture::join);
        assertEquals(0, reportingQueue.getReportingQueue().size());
    }
}