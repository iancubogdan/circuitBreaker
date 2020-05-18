package com.patterns.circuitbreaker.custombuilt;

import com.patterns.circuitbreaker.services.AdeleService;
import com.patterns.circuitbreaker.services.CallerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleCircuitBreakerTest {

    @Autowired
    private CallerService callerService;
    @Autowired
    private AdeleService adeleService;

    @Test
    public void testCaller() {
        callerService.call();
    }

}