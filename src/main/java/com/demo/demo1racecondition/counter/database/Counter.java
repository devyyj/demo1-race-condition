package com.demo.demo1racecondition.counter.database;

import com.demo.demo1racecondition.entity.Product;
import com.demo.demo1racecondition.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
@Transactional
public class Counter {

    private final ProductRepository productRepository;

    public void createItem(String name) {
        Product item = new Product();
        item.setName(name);
        item.setStock(0L);
        productRepository.save(item);
    }

    public void increment(String name) {
        Product item = productRepository.findByName(name);
        item.setStock(item.getStock() + 1);
        productRepository.save(item);
    }

    public Long getCount(String name) {
        Product item = productRepository.findByName(name);
        return item.getStock();
    }
}
