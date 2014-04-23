package com.blstream.myhoard.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(value = 2)
public class TimeMeasurementAspect {
	
	private Logger logger = Logger.getLogger(TimeMeasurementAspect.class.getCanonicalName());
	
	@Pointcut("within(com.blstream.myhoard..*)")
	public void myHoard() {
	}

	@Around("myHoard()")
	public Object getExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
		
		long start = System.currentTimeMillis();
		Object output = pjp.proceed();
		long result = System.currentTimeMillis() - start;
		
		logger.debug("Method name: " + pjp.getSignature().getName() + ", Execution time: "
				+ result + " ms.");
		
		return output;
	}
}
