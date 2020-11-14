package com.kosmo.springapp.basic.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Annotation")
public class ModelAttributeController {
	//방법1)
	//@RequestParam Map사용: 이때는 커맨드 객체(DTO계열 클래스) 생성 불필요
	//단, 체크박스는 여러 개의 값이 넘어와 하나만 받을 수 있다.
	/*
	@RequestMapping("/ModelAttribute.do")
	public String map(@RequestParam Map map, @RequestParam String[] inters, Model model) {
		
		//data 수정
		map.put("inters", Arrays.toString(inters));
		//데이터 request 영역에 저장
		model.addAllAttributes(map);
		//뷰 정보 반환
		return "annotation06/ModelAttribute";
	}
	*/
	
	//방법2) 
	//@ModelAttribute 사용
	//파라미터가 많은 경우 서블릿 AP(HttpServletRequest)보다는
	//@ModelAttribute이 유리하다. 단, 파라미터명과 속성명을 일치시켜야 한다.
	@RequestMapping("/ModelAttribute.do")
	public String command(@ModelAttribute Command cmd, Model model) {//@ModelAttribute는 생략가능
		//데이터 request 영역에 저장
		model.addAttribute("cmd",cmd);//EL에서 cmd.속성명으로 접근이 가능!
		//뷰 정보 반환
		return "annotation06/ModelAttribute";
	}
	
	
	
	
	
}
