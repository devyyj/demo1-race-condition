package com.demo.demo1racecondition.shared.variable;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SharedVariableSyncService {
    private final SharedVariableService sharedVariableService;

    public synchronized void decreaseQuantity(){
        sharedVariableService.decreaseQuantity();
    }
}
