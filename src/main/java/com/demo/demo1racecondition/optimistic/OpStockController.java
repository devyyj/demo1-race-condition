package com.demo.demo1racecondition.optimistic;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/opstock")
public class OpStockController {

    private final OpStockService opStockService;
    private final OpStockRetryService opStockRetryService;

    @PostMapping("/decrease")
    public String decrease() {
        opStockService.decreaseStock();
        return "Success";
    }

    @PostMapping("/decrease-retry")
    public String decreaseRetry() {
        opStockRetryService.decreaseStockRetry();
        return "Success";
    }

}
