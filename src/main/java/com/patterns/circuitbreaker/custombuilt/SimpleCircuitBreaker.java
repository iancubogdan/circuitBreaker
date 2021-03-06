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

    private static int FAILURE_THRESHOLD = 3;
    private int failureCount = 0;

    public void recordFailure() {
        failureCount++;
        System.out.println("Recorded failure, current count is: " + failureCount);
    }

    public void reset() {
        failureCount = 0;
    }

    public State getState() {
        if (failureCount > FAILURE_THRESHOLD) {
            System.out.println("CB State: Open");
            return State.Open;
        }
        System.out.println("CB State: Closed");
        return State.Closed;
    }

    public boolean isOpen() {
        return getState().equals(State.Open);
    }

    public boolean isClosed() {
        return getState().equals(State.Closed);
    }
}
