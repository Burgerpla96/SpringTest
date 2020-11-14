package com.kosmo.springapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//POJO(일반 자바클래스)
//컴파일러에세 controller임을 알리기 위해 annotation하기
@Controller
public class IndexController {
	//컨트롤러 메서드
	//요청 맵핑
	@RequestMapping("/handlermapping.do")
	public String handlerMapping() {
		//뷰정보 반환
		return "handlermapping01/HandlerMapping";
	}//////////handlerMapping
	@RequestMapping("/controller.do")
	public String controller() {
		//뷰정보 반환
		return "controller02/Controller";
	}//////////controllerMapping
	@RequestMapping("/viewresolver.do")
	public String viewresolver() {
		//뷰정보 반환
		return "viewresolver03/ViewResolver";
	}//////////viewresolver
	
	//201109 공부
	@RequestMapping("/returntype.do")
	public String returntype() {
		//뷰정보 반환
		return "returntype04/ReturnType";
	}//////////returntype
	@RequestMapping("/injection.do")
	public String injection() {
		//뷰정보 반환
		return "injection05/Injection";
	}//////////injection
	@RequestMapping("/annotation.do")
	public String annotation() {
		//뷰정보 반환
		return "annotation06/Annotation";
	}//////////annotation
	@RequestMapping("/database.do")
	public String database() {
		//뷰정보 반환
		return "database07/Database";
	}//////////annotation
	@RequestMapping("/resource.do")
	public String resource() {
		//뷰정보 반환
		return "resource08/Resource";
	}//////////resource
	@RequestMapping("/validation.do")
	public String validation() {
		//뷰정보 반환
		return "validation09/Validation";
	}//////////validation
	
	
	
}
