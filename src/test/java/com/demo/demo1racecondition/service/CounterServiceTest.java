package com.demo.demo1racecondition.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CounterServiceTest {

    @Autowired
    private CounterService counterService;

    @Test
    @DisplayName("멀티스레드 환경에서 낙관적락 Counter 테스트")
    public void testOptimisticLockCounter() throws InterruptedException {
        counterService.createItem("item11");

        int threadCount = 100;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                counterService.retryIncrement();
                latch.countDown();
            });
            thread.start();
        }

        latch.await();

        System.out.println(counterService.getCount());
    }

    @Test
    @DisplayName("멀티스레드 환경에서 낙관적락 retryable 애노테이션 적용 Counter 테스트")
    public void testOptimisticLockMaxAttemptsCounter() throws InterruptedException {
        counterService.createItem("item12");

        int threadCount = 100;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                counterService.maxAttemptsIncrement();
                latch.countDown();
            });
            thread.start();
        }

        latch.await();

        System.out.println(counterService.getCount());
    }
}