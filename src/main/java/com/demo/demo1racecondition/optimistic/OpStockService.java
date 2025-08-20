package com.demo.demo1racecondition.optimistic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OpStockService {

    private final OpStockRepository opStockRepository;

    void decreaseStock() {
        OpStock opStock = opStockRepository.findById(1L).get();
        long l = opStock.getQuantity() - 1;
        opStock.setQuantity(l);
    }

}
