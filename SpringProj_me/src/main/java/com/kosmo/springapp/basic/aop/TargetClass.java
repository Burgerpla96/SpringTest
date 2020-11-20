package com.kosmo.springapp.basic.aop;

import lombok.Setter;

/*
 * 공통관점(공통로직)을 주입받는 target class
 */
@Setter //SETTER인젝션 주입 - wiring한다.
public class TargetClass {
	//field
	private int start;
	private int end;
	
	
	//핵심 로직 전후에 공통관점(Around Advice) 주입 - 위빙한다.
	//핵심 로직 (core concern)
	public long getTotal() {
		long total =0;
		for(int i = start; i<=end; i++) total +=i;
		return total;
	}
	
	
}
