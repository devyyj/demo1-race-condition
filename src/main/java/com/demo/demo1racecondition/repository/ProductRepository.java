package com.demo.demo1racecondition.repository;

import com.demo.demo1racecondition.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
}
