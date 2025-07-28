package com.demo.demo1racecondition.service;

import com.demo.demo1racecondition.counter.database.Counter;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CounterService {

    private final Counter counter;

    public void createItem(String name) {
        counter.createItem(name);
    }

    public Long getCount() {
        return counter.getCount();
    }

    public void retryIncrement() {
        while (true) {
            try {
                counter.increment();
                break;
            } catch (ObjectOptimisticLockingFailureException e) {
                System.out.println(e.toString());
            }
        }
    }

    @Retryable(
            retryFor = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 200, multiplier = 2)
    )
    public void maxAttemptsIncrement() {
                counter.increment();
    }

    @Recover
    public void recover(ObjectOptimisticLockingFailureException e) {
        System.out.println("재시도 최종 실패!");
    }
}
