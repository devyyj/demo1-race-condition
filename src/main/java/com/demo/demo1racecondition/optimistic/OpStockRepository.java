package com.demo.demo1racecondition.optimistic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OpStockRepository extends JpaRepository<OpStock,Long> {
}
