package se.yrgo.spring.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class PerformanceTimingAdvice {

    public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable {
        long startTime = System.nanoTime();

        try {
            return method.proceed();
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
