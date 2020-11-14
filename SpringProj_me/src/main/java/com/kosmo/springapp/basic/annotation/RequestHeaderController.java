package com.kosmo.springapp.basic.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestHeaderController {
	
	//방법1] 서블릿 API 사용으로 요청헤더값 알아내기
	/*
	@RequestMapping("/Annotation/RequestHeader.do")
	public String exec(HttpServletRequest req) {
		String referer = req.getHeader("referer");
		//request영역에 저장
		req.setAttribute("referer", referer);
		//view 정보 반환
		return "annotation06/Annotation"; 
	}///////////////exec
	*/
	
	
	//방법 2] 서블릿 API 미사용, @RequestHeader Annotation사용
	@RequestMapping("/Annotation/RequestHeader.do")
	public String exec(@RequestHeader(value = "referer", required = false, defaultValue = "헤더명 없음") String referer, Model model) {
		//request영역에 저장
		model.addAttribute("referer",referer);
		//view 정보 반환
		return "annotation06/Annotation"; 
	}
	
	
	
}
