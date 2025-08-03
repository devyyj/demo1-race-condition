package com.demo.demo1racecondition.distributed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockLockService {

    private final RedissonClient redissonClient;
    private final StockRedisRepository stockRedisRepository;

    /**
     * 분산 락을 적용한 재고 차감 메서드
     */
    public void decreaseStock() {
        String lockKey = "stock:lock:Stock 1";
        RLock lock = redissonClient.getLock(lockKey);

        boolean acquired = false;
        try {
            acquired = lock.tryLock(10, 10, TimeUnit.SECONDS);
            if (!acquired) {
                log.error("락 획득 실패");
                throw new IllegalStateException("락 획득 실패");
            }

            // 3) 락 획득 후 실제 트랜잭션 로직 실행
            decreaseStockLock();

        } catch (InterruptedException e) {
            log.error("락 획득 대기 중 인터럽트 발생");
            Thread.currentThread().interrupt();
            throw new RuntimeException("락 획득 대기 중 인터럽트 발생", e);
        } finally {
            // 4) 반드시 락 해제
            if (acquired && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public void decreaseStockLock() {
        Stock stock = stockRedisRepository.findByName("Stock 1");
        long quantity = stock.getQuantity() - 1;
        if (quantity < 0) {
            log.error("재고 부족");
            throw new IllegalArgumentException("재고 부족");
        }
        stock.setQuantity(quantity);
        stockRedisRepository.save(stock);
    }
}
