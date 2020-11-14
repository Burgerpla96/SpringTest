package com.kosmo.springapp.basic.injection;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InjectionController {
	
	//person은 세터로 주입 받기
	@Resource(name = "personDefault")//setter 안쓰고 값 가져오기
	private Person personDefault;
	
	//@Autowired는 생성자에도 붙일 수 있다. 생성자 인젝션- 여기에 @Resource는 안된다.
	@Autowired
	public InjectionController(Person personDefault) {
		this.personDefault = personDefault;
	}
	
	
	@RequestMapping("/Injection/Injection.do")
	public String execute(@RequestParam Map map, Model model) {//form 에서 파라미터 여러개 map으로 받고 model에 저장
		//사용자 입력값으로 다시 셋팅
		personDefault.setAddr(map.get("addr").toString());
		personDefault.setAge(Integer.valueOf(map.get("age").toString()));
		personDefault.setName(map.get("name").toString());
		//영역에 저장
		model.addAttribute("personInfo", personDefault.toString());
		//view정보 반환
		return "injection05/Injection";
	}/////////////execute
}
