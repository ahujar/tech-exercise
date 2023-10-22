package com.barrenjoey.java.bank.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ReportingQueue {
    private final BlockingQueue<Object> reportingQueue = new LinkedBlockingQueue<Object>();
    public BlockingQueue<Object> getReportingQueue() {
        return reportingQueue;
    }
}
