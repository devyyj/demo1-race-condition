package com.demo.demo1racecondition.counter;

import com.demo.demo1racecondition.entity.Product;
import com.demo.demo1racecondition.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
@Component
//@Transactional
public class SyncCounter {

    private final ProductRepository productRepository;

    public synchronized void increment() {
        Product item = productRepository.findByName("item");
        item.setStock(item.getStock() + 1);
        productRepository.save(item);
    }

    Long getCount() {
        Product item = productRepository.findByName("item");
        return item.getStock();
    }
}