package com.hello.springboothello.ex.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

	// 공통관심사항을 어디에 적용할지 타겟팅
	// execution(* 패키지..*(..)) 보통 패키지 레벨로 많이 함. 조정 가능
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

/*
 * AOP 동작원리*
스프링 : 컨트롤러와 서비스의 의존 관계에 의해 메소드 호출
AOP 어디에 적용 결정 시 : AOP가 가짜 서비스를 만들. 이를 '프록시'라 함
스프링 컨테이너에 빈을 등록할 때 가짜 스프링 빈을 앞에 세워둠. 가짜 스프링 빈 끝나면 joinPoint.proceed() 거쳐 진짜 서비스 호출
그래서 컨트롤러가 호툴하는 것은 진짜 서비스가 아니라 프록시 기술로 발생하는 가짜 서비스임
*/