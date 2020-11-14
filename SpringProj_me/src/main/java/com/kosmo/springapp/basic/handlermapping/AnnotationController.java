package com.kosmo.springapp.basic.handlermapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnnotationController {
	//컨트롤러 메서드
	@RequestMapping("/HandlerMapping/Annotaion.do")
	public String handleRequest(Model model) {
		//데이터 저장
		model.addAttribute("message","[DefaultAnnotationHandlerMapping]");
		//view 정보 반환
		return "handlermapping01/HandlerMapping";
	}
	
	
}
