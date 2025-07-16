package com.demo.demo1racecondition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
class Demo1RaceConditionApplicationTests {

    @Test
    @DisplayName("Counter 클래스 테스트")
    void test1() {
        Counter counter = new Counter();

        counter.increment();
        counter.increment();
        counter.increment();
        counter.increment();
        counter.increment();

        System.out.println(counter.getCount());
    }

    @Test
    @DisplayName("멀티스레드 환경에서 Counter 클래스 테스트")
    void test2() throws InterruptedException {
        Counter counter = new Counter();

        int threadCount = 1000;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    counter.increment();
                }
                latch.countDown();
            });
            thread.start();
        }

        latch.await();

        System.out.println(counter.getCount());
    }

    @Test
    @DisplayName("멀티스레드 환경에서 SyncCounter 클래스 테스트")
    void test3() throws InterruptedException {
        SyncCounter counter = new SyncCounter();

        int threadCount = 1000;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    counter.increment();
                }
                latch.countDown();
            });
            thread.start();
        }

        latch.await();

        System.out.println(counter.getCount());
    }

    @Test
    @DisplayName("멀티스레드 환경에서 LockCounter 클래스 테스트")
    void test4() throws InterruptedException {
        LockCounter counter = new LockCounter();

        int threadCount = 1000;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    counter.increment();
                }
                latch.countDown();
            });
            thread.start();
        }

        latch.await();

        System.out.println(counter.getCount());
    }
}
