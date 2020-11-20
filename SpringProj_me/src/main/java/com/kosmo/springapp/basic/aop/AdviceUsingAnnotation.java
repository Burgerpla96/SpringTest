package com.kosmo.springapp.basic.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect //advice역할 클래스를 의미
public class AdviceUsingAnnotation {
	//xml에 있는 내용
	@Pointcut("execution(public * com.kosmo.springapp.basic.aop..getTotal(..))")
	public void advice() {
		
	}
	
	//어떤 어드바이스를 사용할 지 설정
	@Around("advice()")
	public Object crossCuttingConcern(ProceedingJoinPoint point) throws Throwable {
		//대상 클래스의 핵심 로직(getTotal()) 실행전 수행할 공통 로직
		//삽입할 대상 클래스의 메서드명 얻기 (확인용)
		String coreConcernName = point.getSignature().toShortString();
		System.out.println("대상 클래스의 핵심관점(method)명: "+coreConcernName);
		long startTime = System.currentTimeMillis();
		//대상 클래스의 핵심 로직(getTotal()) 실행
		//invoke: 어떤 상황에서 자동으로 호출되는 것
		Object object = point.proceed();//invoke된다.
		//대상 클래스의 핵심 로직 실행후 수행할 공통 로직
		long endTime = System.currentTimeMillis();
		System.out.println(coreConcernName+"의 총 소요 실행시간: "+(endTime-startTime)/1000.0+"초");
		
		return object;
	}////////
	
	
	
}
