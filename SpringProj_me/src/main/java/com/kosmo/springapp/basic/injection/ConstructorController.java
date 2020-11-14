package com.kosmo.springapp.basic.injection;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConstructorController {
	
	/* DI(Dependency Injection)
	 * 하나의 개체 안에서 다른 개체를 new연산자를 이용해서 생성시 두 개체간에 결합도가 높아지고 독립성이 떨어지게 된다.
	 * 
	 * 따라서 개체 스스로 new를 통해 필요한 개체를 생성하지 않고  
	 * 설정파일(xml)에서 주입을 받아 낮은  결합도를 유지 하는게 목적
	*/
	
	//생성자 인젝션을 받기 위한 단계
	//1) 주입받는 타입의 갯수에 맞게 맴버변수 선언
	private Person personType,personIndex;
	//2) 인자 생성자
	public ConstructorController(Person personType, Person personIndex) {
		this.personType = personType;
		this.personIndex = personIndex;
	}////////ConstructorController
	
	
	

	@RequestMapping("/Injection/Constructor.do")
	public String execute(Map map) {
		//데이터 저장
		map.put("personInfo", personType+"<br/>"+personIndex);//문자열 더하기를 안할경우 toString() 써야함!
		//뷰 정보 반환
		return "injection05/Injection";
	}///////////execute
	
	
	
}
