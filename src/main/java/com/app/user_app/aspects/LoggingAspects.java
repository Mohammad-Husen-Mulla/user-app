package com.app.user_app.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Aspect
public class LoggingAspects {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution (* com.app.user_app.service.impl.*.*(..))")
    public void appUserServiceImplExecutionAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.debug("{} Method Execution Started", methodName);
        joinPoint.proceed();
        logger.debug("{} Method Execution Ended", methodName);
    }

    @AfterThrowing( value = "execution (* com.app.user_app.service.impl.*.*(..))", throwing = "ex")
    public void appUserServiceImplExecutionAdviceExceptionAdvice(Exception ex){
        logger.error("{} EXCEPTION..................", Arrays.toString(ex.getStackTrace()));
    }

}
