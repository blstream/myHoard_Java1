package com.blstream.myhoard.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class ArgsMethodAspect {

	private Logger logger = Logger.getLogger(ArgsMethodAspect.class
			.getCanonicalName());

	@Pointcut("within(com.blstream.myhoard..*)")
	public void getArgs() {

	}

	@Before("getArgs()")
	public void getArgsDemo(JoinPoint joinPoint) {
		logger.debug("Class name: "
				+ joinPoint.getTarget().getClass().getName());
		logger.debug("Method name: " + joinPoint.getSignature().getName());
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String[] parameterNames = signature.getParameterNames();
		if (parameterNames != null) {
			logger.debug("Method args name: ");
			for (String name : parameterNames) {
				logger.debug(name);
			}
		}
		logger.debug("Method args value: ");
		for (Object arg : joinPoint.getArgs()) {
			logger.debug(arg);
		}
		logger.debug("\n");
	}

}
