package com.barrenjoey.java.psr.service;

public interface InputService {
    Integer readInt();
    String readString();
    boolean readBoolean();
    void close();
}
