package com.bankdemo.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by Ilyas.Kuanyshbekov on 14.09.2016.
 */
@Aspect
@EnableAspectJAutoProxy
@Component
@Configuration
public class BankAspectAnnotationDriven {

    private static final Logger logger = Logger.getLogger(BankAspectAnnotationDriven.class);


    @Pointcut("execution(* com.bankdemo.ws.soap..*(..))")
    public void webServiceMethod() {}


    @Around("webServiceMethod()")
    public Object aroundWebService(ProceedingJoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        logger.info("Calling method " +  method + " with args " + args);

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error(throwable.getCause());
        }

        logger.info("Method " + method + " returns " + result);

        return result;
    }




}
