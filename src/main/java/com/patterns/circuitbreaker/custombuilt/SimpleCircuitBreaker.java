package com.patterns.circuitbreaker.custombuilt;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Component
public class SimpleCircuitBreaker {

    enum State {
        Open, Closed, HalfOpen
    }

    private static int FAILURE_THRESHOLD = 3;
    private int failureCount = 0;
    private int RESET_TIMEOUT = 1000;
    private long lastFailTime = Long.MAX_VALUE;

    public void recordFailure() {
        lastFailTime = new Date().getTime();
        failureCount++;
        System.out.println("Recorded failure, current count is: " + failureCount);
    }

    public void reset() {
        failureCount = 0;
        lastFailTime = Long.MAX_VALUE;
    }

    public State getState() {
        if (failureCount > FAILURE_THRESHOLD &&
            (new Date().getTime() - lastFailTime) > RESET_TIMEOUT) {
            System.out.println("CB State: HalfOpen");
            return State.HalfOpen;
        }

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
