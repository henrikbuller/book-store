package se.yrgo.spring.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class PerformanceTimingAdvice {

    public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable {
        long startTime = System.nanoTime();

        try {
            Object value = method.proceed();
            return value;
        } finally {
            long endTime = System.nanoTime();
            long timeTaken = endTime - startTime;
            System.out.println("The method " +
                    method.getSignature().getName() + " took " + timeTaken
                    / 1000000);
        }
    }

    public void beforeAdviceTesting(JoinPoint jp) {
        System.out.println("Now entering method " + jp.getSignature().getName());
    }
}
