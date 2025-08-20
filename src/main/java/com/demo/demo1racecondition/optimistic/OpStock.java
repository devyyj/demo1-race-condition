package com.demo.demo1racecondition.optimistic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OpStock {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    @Version
    private Long version;
}
