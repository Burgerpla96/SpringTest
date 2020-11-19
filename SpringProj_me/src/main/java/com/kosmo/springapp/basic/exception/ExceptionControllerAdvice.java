package com.kosmo.springapp.basic.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice//개발시에는 주석하기
public class ExceptionControllerAdvice {
	
	@ExceptionHandler({Exception.class})
	public String exception(Exception e, Model model) {
		model.addAttribute("errorMsg","오류발생 삐용삐용!");
		//뷰 정보 반환
		return "exception12/Exception";
	}
	
}
