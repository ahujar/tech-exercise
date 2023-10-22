package com.barrenjoey.java.psr.service.impl;

import com.barrenjoey.java.psr.service.InputService;

import java.util.Scanner;

public class ScannerInputService implements InputService {
    public static final Scanner scanner = new Scanner(System.in);

    @Override
    public Integer readInt() {
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String readString() {
        return scanner.nextLine();
    }

    @Override
    public boolean readBoolean() {
        String input = scanner.nextLine();
        return "y".equalsIgnoreCase(input);
    }

    @Override
    public void close() {
        scanner.close();
    }
}
