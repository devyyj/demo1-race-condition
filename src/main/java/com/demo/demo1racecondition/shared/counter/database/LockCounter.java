package com.demo.demo1racecondition.shared.counter.database;

import com.demo.demo1racecondition.shared.entity.Product;
import com.demo.demo1racecondition.shared.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

@Component
@Transactional
@RequiredArgsConstructor
public class LockCounter {
    private final ProductRepository productRepository;

    private String name;

    private final ReentrantLock lock = new ReentrantLock();

    public void createItem(String name) {
        this.name = name;
        Product item = new Product();
        item.setName(name);
        item.setStock(0L);
        productRepository.save(item);
    }

    public void increment() {
        lock.lock();
        try {
            Product item = productRepository.findByName(name);
            item.setStock(item.getStock() + 1);
            productRepository.save(item);
        } finally {
            lock.unlock();
        }
    }

    public Long getCount() {
        Product item = productRepository.findByName(name);
        return item.getStock();
    }
}
