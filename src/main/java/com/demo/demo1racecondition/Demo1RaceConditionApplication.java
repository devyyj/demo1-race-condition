package com.demo.demo1racecondition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class Demo1RaceConditionApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo1RaceConditionApplication.class, args);
    }

}
