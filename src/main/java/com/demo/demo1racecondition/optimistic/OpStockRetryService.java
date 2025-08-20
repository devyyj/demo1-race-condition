package com.demo.demo1racecondition.optimistic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpStockRetryService {

    private final OpStockService opStockService;

    void decreaseStockRetry() {
        while (true) {
            try {
                opStockService.decreaseStock();
                break;
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
