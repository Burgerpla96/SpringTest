package com.kosmo.springapp.basic.resource;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  
 *  1. 확장자가 .properties인 리소스 파일 src/main/resources 에 생성
 *  2. servlet-context.xml 파일에 <context:property-placeholder>등록
 *  3. 클래스에서 사용시에는 @Value Annotation 사용
 */


@Controller
public class ResourcesController {

	//@Value 어노테이션 -- .properties(리소스파일) 에 있는 키값을 가져올 수 있다.
	//예) @Value("${키값}")
	//    private 자료형 받을 변수
	
	@Value("${name}")
	private String name;
	@Value("${user}")
	private String username;
	@Value("${pass}")
	private String password;
	
	//객체 주입
	@Resource(name = "command")//설정파일에서 beans:bean tag의 id속성을 name으로 접근한다.
	private UserCommand command;
	
	
	@RequestMapping("/Resource/Resource.do")
	public String exec(Model model) {
		//데이터 저장
		model.addAttribute("resource",command);
		model.addAttribute("value",String.format("[name=%s, username=%s, password=%s]", 
				name, username, password));
		
		//뷰정보 반환
		return "resource08/Resource";
	}///////exec
	
	
}
