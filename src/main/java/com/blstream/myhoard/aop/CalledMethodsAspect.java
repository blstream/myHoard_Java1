package com.blstream.myhoard.aop;

import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

@Aspect
@Order(value = 3)
public class CalledMethodsAspect {

    private static final long LOG_INTERVAL = 10; // default: 10 seconds
    private static final Logger logger = Logger.getLogger(CalledMethodsAspect.class.getCanonicalName());

    private HashMap<String, Integer> calledMethodsMap = new HashMap<>();
    private final Date startDate = new Date();
    private Date lastLogDate = new Date();

    @Value("${aop.calledMedhodsAspect:true}")
    private boolean enabled;

    @Pointcut("within(com.blstream.myhoard..*)")
    public void myHoard() {
    }

    @Before("myHoard()")
    public void logStats(JoinPoint joinPoint) throws Throwable {
        if (enabled) {
            final String methodName = joinPoint.getSignature().toShortString();

            if (calledMethodsMap.containsKey(methodName)) {
                int count = calledMethodsMap.get(methodName);
                calledMethodsMap.put(methodName, ++count);
            } else {
                calledMethodsMap.put(methodName, 1);
            }

            Date now = new Date();
            long lastLogAge = (now.getTime() - lastLogDate.getTime()) / 1000; // time since last log (seconds)

            if (lastLogAge > LOG_INTERVAL) {
                logger.debug(String.format("\n\nMethods called between %s - %s:\n", startDate.toString(), now.toString()));
                logger.debug(calledMethodsMap.toString());
                lastLogDate = now;
            }
        }
    }

    public HashMap<String, Integer> getCalledMethodsMap() {
        return calledMethodsMap;
    }

    public void setCalledMethodsMap(HashMap<String, Integer> calledMethodsMap) {
        this.calledMethodsMap = calledMethodsMap;
    }

    public Date getLastLogDate() {
        return lastLogDate;
    }

    public void setLastLogDate(Date lastLogDate) {
        this.lastLogDate = lastLogDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getStartDate() {
        return startDate;
    }
}
