package com.demo.demo1racecondition.shared.counter.variable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SyncCounter {

    private Long count = 0L;

    public synchronized void increment() {
        count++;
    }

    public Long getCount() {
        return count;
    }
}