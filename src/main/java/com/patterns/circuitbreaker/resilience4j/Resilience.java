package com.patterns.circuitbreaker.resilience4j;

import com.patterns.circuitbreaker.services.AdeleService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Resilience {

    public static void main(String[] args) throws InterruptedException {

        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(5)
                .minimumNumberOfCalls(4)
                .recordExceptions(Throwable.class)
                .waitDurationInOpenState(Duration.ofMillis(15))
                .permittedNumberOfCallsInHalfOpenState(1)
                .build();
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);

        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("cb");
        AdeleService adeleService = new AdeleService();

        for (int i = 0; i < 30; i++) {
            System.out.println("________________________________________________\n" + "Call #" + i );
            Supplier<String> decorated = CircuitBreaker.decorateSupplier(cb, () -> adeleService.answerBack());
            String result = Try.ofSupplier(decorated).recover(throwable -> "Better luck next time").get();

            if (i == 5) {
                adeleService.setDoNotDisturb(true);
            }

            if (i == 8) {
                Thread.sleep(20);
                adeleService.setDoNotDisturb(false);
            }

            System.out.println(result);
        }
    }
}
