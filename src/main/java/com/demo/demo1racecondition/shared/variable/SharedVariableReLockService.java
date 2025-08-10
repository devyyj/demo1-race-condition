package com.demo.demo1racecondition.shared.variable;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class SharedVariableReLockService {
    private final SharedVariableService sharedVariableService;

    private final Lock lock = new ReentrantLock();

    public void decreaseQuantity() {
        lock.lock();
        try {
            sharedVariableService.decreaseQuantity();
        } finally {
            lock.unlock();
        }
    }
}
