package com.demo.demo1racecondition.shared.variable;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/variable")
@RequiredArgsConstructor
public class SharedVariableController {

    private final SharedVariableService sharedVariableService;
    private final SharedVariableSyncService  sharedVariableSyncService;
    private final SharedVariableReLockService sharedVariableReLockService;

    @PostMapping("/decrease")
    public String decrease(){
        sharedVariableService.decreaseQuantity();
        return "Success!";
    }

    @PostMapping("/sync-decrease")
    public String syncDecrease(){
        sharedVariableSyncService.decreaseQuantity();
        return "Success!";
    }

    @PostMapping("/lock-decrease")
    public String lockDecrease(){
        sharedVariableReLockService.decreaseQuantity();
        return "Success!";
    }

}
