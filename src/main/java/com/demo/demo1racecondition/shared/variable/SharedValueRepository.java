package com.demo.demo1racecondition.shared.variable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedValueRepository extends JpaRepository<SharedVariable, Long> {
}
