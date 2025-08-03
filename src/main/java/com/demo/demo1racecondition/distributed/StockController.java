package com.demo.demo1racecondition.distributed;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final StockLockService stockLockService;


    @PostMapping("/create")
    public String create() {
        stockService.createStock();
        return "Success!";
    }

    @PostMapping("/decrease")
    public String decrease() {
        stockService.decreaseStock();
        return "Success!";
    }

    @PostMapping("/redis-decrease")
    public String redisDecrease() {
        stockLockService.decreaseStock();
        return "Success!";
    }

}
