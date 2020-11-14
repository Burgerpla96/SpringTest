package com.kosmo.springapp.basic.injection;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SetterController {
	/*
	 * 현재 클래스가 Person 객체 필요.
	 * new 하지 않고 세터를 통해서 주입 받자 
	 */
	
	//1) 맴버변수(속성) 선언
	private Person personType, personProperty;
	//2) setter 생성하기
	@Resource
	@Qualifier("person1")
	public void setPersonType(Person personType) {
		this.personType = personType;
	}
	@Resource
	@Qualifier("person3")
	public void setPersonProperty(Person personProperty) {
		this.personProperty = personProperty;
	}
	//@Resource 를 하면 servlet-context에서 id를 기준으로 찾아서 injection한다.
	//만약 id에 없으면 type으로 찾아서 injection하는데 type이 여러개면 혼동...
	//@Qualifier를 추가적으로 붙이면 마지막으로 quailfier의 value값으로 찾는다. 
	

	@RequestMapping("/Injection/Setter.do")
	public String execute(Map map) {
		//데이터 저장
		map.put("personInfo", personType+"<br/>"+personProperty);
		//view 정보 반환
		return "injection05/Injection";
	}
}
