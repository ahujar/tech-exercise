package com.barrenjoey.java.bank.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ReportingService {
    List<CompletableFuture<Void>> report();

    void gracefulShutdown() throws InterruptedException;
}
