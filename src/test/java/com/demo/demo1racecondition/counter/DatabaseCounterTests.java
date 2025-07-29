package com.demo.demo1racecondition.counter;

import com.demo.demo1racecondition.counter.database.Counter;
import com.demo.demo1racecondition.counter.database.LockCounter;
import com.demo.demo1racecondition.counter.database.SyncCounter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class DatabaseCounterTests {

    @Autowired
    private Counter counter;
    @Autowired
    private SyncCounter syncCounter;
    @Autowired
    private LockCounter lockCounter;

    @Test
    @DisplayName("기본 테스트")
    public void testCounter() {
        String item1 = "item1";
        counter.createItem(item1);

        counter.increment(item1);
        counter.increment(item1);
        counter.increment(item1);
        counter.increment(item1);
        counter.increment(item1);

        System.out.println(counter.getCount(item1));
    }

    @Test
    @DisplayName("멀티스레드 환경에서 Counter 테스트")
    @Disabled
    public void testCounterWithMultiThread() throws InterruptedException {
        counter.createItem("item2");

        int threadCount = 100;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                counter.increment("item2");
                latch.countDown();
            });
            thread.start();
        }

        latch.await();

        System.out.println(counter.getCount("item2"));
    }

    @Test
    @DisplayName("멀티스레드 환경에서 SyncCounter 테스트")
    public void testSyncCounterWithMultiThread() throws InterruptedException {
        syncCounter.createItem("item3");

        int threadCount = 100;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                syncCounter.increment();
                latch.countDown();
            });
            thread.start();
        }

        latch.await();

        System.out.println(syncCounter.getCount());
    }

    @Test
    @DisplayName("멀티스레드 환경에서 LockCounter 테스트")
    public void testLockCounterWithMultiThread() throws InterruptedException {
        lockCounter.createItem("item4");

        int threadCount = 100;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                lockCounter.increment();
                latch.countDown();
            });
            thread.start();
        }

        latch.await();

        System.out.println(lockCounter.getCount());
    }

}
