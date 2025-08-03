package com.demo.demo1racecondition.shared.counter.database;

import com.demo.demo1racecondition.shared.entity.Product;
import com.demo.demo1racecondition.shared.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
@Transactional
@Slf4j
public class Counter {

    private final ProductRepository productRepository;

    public void createItem(String name) {
        Product item = new Product();
        item.setName(name);
        item.setStock(0L);
        productRepository.save(item);
    }

    public void decrease(String name) {
        Product item = productRepository.findByName(name);
        if(item.getStock() < 1) {
            log.error("낙관적락 재고 없음");
            throw new IllegalArgumentException("재고 없음");
        }
        item.setStock(item.getStock() - 1);
        productRepository.save(item);
    }

    public Long getCount(String name) {
        Product item = productRepository.findByName(name);
        return item.getStock();
    }
}
