package com.hello.springboothello.ex.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

	// 공통관심사항을 어디에 적용할지 타겟팅
	// execution(*) 
	@Around("execution(* com.hello.springboothello..*(..)) ")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("START: " + joinPoint.toString());
		try {
			return joinPoint.proceed();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
		}
	}
}

/*
빈 등록 2가지 방법
1. @Component
2. SpringConfig @Bean
*/