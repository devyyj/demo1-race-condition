package com.demo.demo1racecondition.shared.controller;

import com.demo.demo1racecondition.shared.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/counter")
public class CounterController {

    private final CounterService counterService;

    @PostMapping("/create")
    public String create() {
        counterService.createItem("test");
        return "OK";
    }

    @PostMapping("/decrease")
    public String counter() {
        counterService.retryDecrease("test");
        return "OK";
    }

}
