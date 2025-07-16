package com.demo.demo1racecondition;

public class SyncCounter {
    public int getCount() {
        return count;
    }

    synchronized public void increment() {
        this.count++;
    }

    private int count = 0;
}
