package com.kosmo.springapp.basic.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExcetionController {
	//방법1) spring API 미사용 try-catch 절로 예외 처리
	//단, 파일업로드 초과나  ModelAttribute으로 속성값 읽을 때 없을 시,
	//@RequestParam int years에 문자열로 입력받아 올시의 경우는 어노테이션으로 처리해야만 한다!!
	/*
	@RequestMapping("/Exception/Exception.do")
	public String execute(@RequestParam String years, Model model) {
		try {
			//string으로 받게 하기
			//데이터 저장
			model.addAttribute("message","[당신의 5년후 나이는: "+(Integer.parseInt(years)+5)+"살]");
		} catch(NumberFormatException e){
			model.addAttribute("message","[나이는 숫자에 불과...]");
		}
		//뷰 정보 반환
		return "exception12/Exception";
	}
	*/
	
	
	//방법 2) @ExceptionHandler 사용 -- 문제는 예외처리하려는 모든 컨트롤러 마다 작성해야한다.
	/*
	@ExceptionHandler({NumberFormatException.class})
	public String error(Exception e, Model model) {
		model.addAttribute("errorMsg","나이는 숫자에 불과하다!");
		//뷰 정보 반환
		return "exception12/Exception";
	}
	*/
	@RequestMapping("/Exception/Exception.do")
	public String execute(@RequestParam int years, Model model) {
		//데이터 저장
		model.addAttribute("message","[당신의 5년후 나이는: "+(years+5)+"살]");
		//뷰 정보 반환
		return "exception12/Exception";
	}
	
	
	//방법 3) 모든 예외 한 번에 처리.
	//@ControllerAdvice..
	
	
	
}
