package com.patterns.circuitbreaker.services;

import com.patterns.circuitbreaker.custombuilt.MyCircuitBreaker;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallerService {

    @Autowired
    private AdeleService adeleService;

    @MyCircuitBreaker
    public void call() {
        adeleService.answer();
    }
}
