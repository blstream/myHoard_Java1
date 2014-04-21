package com.blstream.myhoard.aop;

import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

// TODO RT - ability to disabling this aspect in application.properties file
@Aspect
@Order(value = 3)
public class CountingCalledMethodsAspect {

    private static final Logger logger = Logger.getLogger(CountingCalledMethodsAspect.class.getCanonicalName());

    private static HashMap<String, Integer> calledMethodsMap = new HashMap<>();
    private static final Date startDate = new Date();

    @Pointcut("within(com.blstream.myhoard..*)")
    public void myHoard() {
    }

    @Around("myHoard()")
    public Object logStats(ProceedingJoinPoint joinPoint) throws Throwable {

        final String methodName = joinPoint.getSignature().toShortString();

        if (calledMethodsMap.containsKey(methodName)) {
            int count = calledMethodsMap.get(methodName);
            calledMethodsMap.put(methodName, ++count);
        } else {
            calledMethodsMap.put(methodName, 1);
        }

        logger.debug(String.format("Methods called between: %s --- %s", startDate.toString(), new Date().toString()));
        logger.debug(calledMethodsMap.toString());

        Object result = joinPoint.proceed();
        return result;
    }

    public static HashMap<String, Integer> getCalledMethodsMap() {
        return calledMethodsMap;
    }

    public static void setCalledMethodsMap(HashMap<String, Integer> calledMethodsMap) {
        CountingCalledMethodsAspect.calledMethodsMap = calledMethodsMap;
    }

}
