package com.demo.demo1racecondition.distributed;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class StockService {

    private final StockRepository stockRepository;

    public void createStock() {
        Stock stock = new Stock();
        stock.setName("Stock 1");
        stock.setQuantity(1000L);
        stockRepository.save(stock);
    }

    public void decreaseStock() {
        Stock stock = stockRepository.findByName("Stock 1");
        long quantity = stock.getQuantity() - 1;
        if (quantity < 0) {
            log.error("재고 부족");
            throw new IllegalArgumentException("재고 부족");
        }
        stock.setQuantity(quantity);
        stockRepository.save(stock);
    }
}
