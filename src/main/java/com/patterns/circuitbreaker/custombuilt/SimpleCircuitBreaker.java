package com.patterns.circuitbreaker.custombuilt;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class SimpleCircuitBreaker {

    enum State {
        Open, Closed
    }

    private static int FAILURE_THRESHOLD = 5;
    private int failureCount = 0;
    private LocalDateTime lastFailTime;

    public void recordFailure() {
        failureCount++;
    }

    public void reset() {
        failureCount = 0;
    }

    public State getState() {
        if (failureCount > FAILURE_THRESHOLD) {
            return State.Open;
        }
        return State.Closed;
    }
}
