package com.kosmo.springapp.basic.annotation;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @Resource
 * 
 * -속성과 setter에만 붙일 수 있다. (생성자는 불가!!!, 생성자는 Autowired만 가능!)
 * -@Autowired와 같다.
 * 즉, 자동으로 주입을 받는다.
 * -Name(id)기반이다.
 * -@Resource(name="설정파일에 등록한 빈의 id값")식으로 와이어링
   *  ❤ 설정파일이 아닌 어노테이션(@Service, @Repository등)을 통해 생성된 빈의
 * 	 id는 지정하지 않는 경우 소문자로 시작하는 클래스명이 id가 된다.
 * 
 * @Autowired와 차이점
 * -required 속성이 없다.
 * -@Resource사용시에는 빈 설정 파일에 <context:annotation-config />설정(비메이븐시)
 * 
 */


@Controller
public class ResourceController {
	
	@Resource(name = "fCommand")
	private Command fCmd;
	@Resource(name = "sCommand")
	private Command sCmd;
	@RequestMapping("/Annotation/Resource.do")
	public String exec(Model model) {
		model.addAttribute("message",String.format("<br/>fCmd: %s<br/> sCmd: %s", fCmd, sCmd));
		return "annotation06/Annotation";  
	}
	
}
