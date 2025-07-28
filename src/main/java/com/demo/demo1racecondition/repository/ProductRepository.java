package com.demo.demo1racecondition.repository;

import com.demo.demo1racecondition.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Product findByName(String name);
}
