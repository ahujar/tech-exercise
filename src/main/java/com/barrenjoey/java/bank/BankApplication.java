package com.barrenjoey.java.bank;

import com.barrenjoey.java.bank.model.AccountStore;
import com.barrenjoey.java.bank.model.ReportingQueue;
import com.barrenjoey.java.bank.service.*;
import com.barrenjoey.java.bank.service.impl.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankApplication {
    public final static Logger logger = Logger.getLogger(BankApplication.class.getName());
    private static ReportingService asyncReporter;
    private static CSVLoader csvLoader;
    private static Bank bank;

    public static void main(String[] args) throws InterruptedException {
        initialize();
        List<CompletableFuture<Void>> report = asyncReporter.report();
        csvLoader.load(List.of("src/main/resources/transaction-log.1.csv", "src/main/resources/transaction-log.2.csv", "src/main/resources/transaction-log.3.csv"));
        logger.log(Level.INFO, "Balance for 52222: " + bank.getAccountById(52222).getBalance());
        asyncReporter.gracefulShutdown();
        report.forEach(CompletableFuture::join);

    }

    private static void initialize() {
        AccountStore accountStore = new AccountStore();
        ReportingQueue reportingQueue = new ReportingQueue();
        ReportingServer reportingServer = new LegacyReportingServer();
        BankAccountService bankAccountService = new BankAccountServiceImpl(accountStore);
        csvLoader = new ParallelStreamCsvLoader(bankAccountService, reportingQueue);
        bank = new BankImpl(accountStore);
        asyncReporter = new AsyncReporter(reportingServer, reportingQueue);
    }
}
