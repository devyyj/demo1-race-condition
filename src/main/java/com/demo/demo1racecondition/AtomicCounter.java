package com.demo.demo1racecondition;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private final AtomicInteger count = new AtomicInteger(0);

    public int getCount() {
        return count.get();
    }

    synchronized public void increment() {
        this.count.incrementAndGet();
    }

}
