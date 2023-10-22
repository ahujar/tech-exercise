package com.barrenjoey.java.bank.service.impl;

import com.barrenjoey.java.bank.model.AccountEntry;
import com.barrenjoey.java.bank.model.ReportingQueue;
import com.barrenjoey.java.bank.service.ReportingServer;
import com.barrenjoey.java.bank.service.ReportingService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class AsyncReporter implements ReportingService {

    public final static Logger logger = Logger.getLogger(AsyncReporter.class.getName());
    private final ReportingServer reportingServer;
    private final ReportingQueue reportingQueue;

    public AsyncReporter(ReportingServer reportingServer, ReportingQueue reportingQueue) {
        this.reportingServer = reportingServer;
        this.reportingQueue = reportingQueue;
    }

    @Override
    public List<CompletableFuture<Void>> report() {
        return IntStream.range(0, Runtime.getRuntime().availableProcessors()).mapToObj(i -> CompletableFuture.runAsync(() -> {
            boolean run = true;
            while (run) {
                try {
                    Object take = this.reportingQueue.getReportingQueue().take();
                    if (take instanceof AccountEntry entry) {
                        this.reportingServer.reportActivity(entry.accountId(), entry.transactionTime(), entry.amount(), entry.effectiveBalance());
                        logger.log(Level.INFO, "Reported account entry: " + entry);
                    } else if (take instanceof String str && str.equalsIgnoreCase("STOP")) {
                        run = false;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        })).toList();
    }

    @Override
    public void gracefulShutdown() {
        IntStream.range(0, Runtime.getRuntime().availableProcessors()).forEach(i -> {
            try {
                this.reportingQueue.getReportingQueue().put("STOP");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
