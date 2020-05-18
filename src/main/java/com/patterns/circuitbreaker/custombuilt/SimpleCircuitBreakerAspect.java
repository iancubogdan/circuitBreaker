package com.patterns.circuitbreaker.custombuilt;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SimpleCircuitBreakerAspect {

    @Autowired
    private SimpleCircuitBreaker cb;

    @Around("@annotation(com.patterns.circuitbreaker.custombuilt.MyCircuitBreaker)")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("NSA HERE: Phone line is tapped!");
        Object returnedObject = null;

        returnedObject = joinPoint.proceed();

        System.out.println("NSA HERE: Phone line disconnected!");
        return returnedObject;
    }
}
