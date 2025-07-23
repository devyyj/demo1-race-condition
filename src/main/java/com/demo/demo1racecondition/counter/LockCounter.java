package com.demo.demo1racecondition.counter;

import java.util.concurrent.locks.ReentrantLock;

public class LockCounter {
    private int count = 0;

    private final ReentrantLock lock = new ReentrantLock();

    public int getCount() {
        return this.count;
    }

    public void increment() {
        lock.lock();
        try {
            this.count++;

        } finally {
            lock.unlock();
        }
    }

}
