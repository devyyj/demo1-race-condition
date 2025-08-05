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

    @PostMapping("/decrease")
    public String decrease(){
//        sharedVariableSyncService.decreaseQuantity();
        sharedVariableService.decreaseQuantity();
        return "Success!";
    }

}
