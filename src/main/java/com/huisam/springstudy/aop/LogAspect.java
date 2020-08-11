package com.huisam.springstudy.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Log4j2
class LogAspect {

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object proceed = joinPoint.proceed();

        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        return proceed;
    }

    @AfterThrowing(pointcut = "@annotation(ExceptionCatcher)", throwing = "e")
    public void exceptionCatching(JoinPoint joinPoint, RuntimeException e) {
        final Signature signature = joinPoint.getSignature();
        final String className = signature.getDeclaringTypeName();
        final String methodName = signature.getName();

        log.info("catch exception on [{}]-{}", className, methodName);
    }
}
