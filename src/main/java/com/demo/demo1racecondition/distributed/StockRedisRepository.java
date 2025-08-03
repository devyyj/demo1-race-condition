package com.demo.demo1racecondition.distributed;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRedisRepository extends JpaRepository<Stock, Long> {
    Stock findByName(String name);
}
