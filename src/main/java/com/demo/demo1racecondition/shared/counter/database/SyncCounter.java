package com.demo.demo1racecondition.shared.counter.database;

import com.demo.demo1racecondition.shared.entity.Product;
import com.demo.demo1racecondition.shared.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
//@Transactional
public class SyncCounter {

    private final ProductRepository productRepository;

    private String name;

    public void createItem(String name) {
        this.name = name;
        Product item = new  Product();
        item.setName(name);
        item.setStock(0L);
        productRepository.save(item);
    }

    public synchronized void increment() {
        Product item = productRepository.findByName(name);
        item.setStock(item.getStock() + 1);
        productRepository.save(item);
    }

    public Long getCount() {
        Product item = productRepository.findByName(name);
        return item.getStock();
    }
}