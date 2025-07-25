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

    private String name;

    public void retryIncrement() {
        while (true) {
            try {
                increment();
                break;
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public void createItem(String name) {
        this.name = name;
        Product item = new Product();
        item.setName(name);
        item.setStock(0L);
        productRepository.save(item);
    }

    public void increment() {
        Product item = productRepository.findByName(name);
        item.setStock(item.getStock() + 1);
        productRepository.save(item);
    }

    public Long getCount() {
        Product item = productRepository.findByName(name);
        return item.getStock();
    }
}
