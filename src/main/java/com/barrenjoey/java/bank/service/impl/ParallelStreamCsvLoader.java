package com.barrenjoey.java.bank.service.impl;

import com.barrenjoey.java.bank.model.AccountEntry;
import com.barrenjoey.java.bank.model.Action;
import com.barrenjoey.java.bank.model.ReportingQueue;
import com.barrenjoey.java.bank.service.BankAccountService;
import com.barrenjoey.java.bank.service.CSVLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ParallelStreamCsvLoader implements CSVLoader {

    public final static Logger logger = Logger.getLogger(ParallelStreamCsvLoader.class.getName());
    private final BankAccountService bankAccountService;
    private final ReportingQueue reportingQueue;

    public ParallelStreamCsvLoader(BankAccountService bankAccountService, ReportingQueue reportingQueue) {
        this.bankAccountService = bankAccountService;
        this.reportingQueue = reportingQueue;
    }

    @Override
    public void load(List<String> csvfiles) {
        csvfiles.stream().sorted().map(Path::of).forEach(path -> {
            logger.log(Level.INFO, "Processing file: " + path);
            Instant start = Instant.now();
            try (Stream<String> stream = Files.lines(path)) {
                stream.parallel().skip(1).forEach(line -> {
                    String[] split = line.split(",");
                    int accountId = Integer.parseInt(split[0]);
                    Action action = Action.fromString(split[1]);
                    double amount = Double.parseDouble(split[2]);
                    Instant transactionTime = Instant.now();
                    double balance = bankAccountService.apply(accountId, amount, action, transactionTime);
                    AccountEntry entry = new AccountEntry(accountId, action, amount, transactionTime, balance);
                    // in case our consumers become really slow in processing of reporting
                    // We can use blockingqueue.put here for reliability
                    reportingQueue.getReportingQueue().offer(entry);
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Instant stop = Instant.now();
            logger.log(Level.INFO, "Duration: " + Duration.between(start, stop).toMillis() + " ms File Processed : " + path);
        });
    }
}
