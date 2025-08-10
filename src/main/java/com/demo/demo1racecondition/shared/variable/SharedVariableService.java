package com.demo.demo1racecondition.shared.variable;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SharedVariableService {

    private final SharedValueRepository sharedValueRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void decreaseQuantity() {
        SharedVariable variable = sharedValueRepository.findById(1L).get();
        variable.setQuantity(variable.getQuantity() - 1);
        sharedValueRepository.save(variable);
    }

}
