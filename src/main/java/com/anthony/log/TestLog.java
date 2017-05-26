package com.anthony.log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Anthony on 2017/5/26.
 */
@Aspect
@Component
public class TestLog {

    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.anthony.*..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable
    {
        logger.info(joinPoint.getTarget().getClass().getName());
    }
}
