package com.demo.demo1racecondition.counter.variable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Counter {

    private Long count = 0L;

    public void increment() {
        count++;
    }

    public Long getCount() {
        return count;
    }
}
