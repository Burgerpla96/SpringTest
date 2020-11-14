package com.kosmo.springapp.basic.viewresolver;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

//controller class
@Controller
public class ViewResolverController {
	/*
	//controller method
	@RequestMapping("/ViewResolver/ViewResolver.do")
	public String execute(Model model) {
		//dispatcher servlet이 받고 request영역에 저장후 view에 forward시킴
		//data save
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		model.addAttribute("message",dateFormat.format(new Date())); //default로 request영역에 저장된다.
		//view정보 반환
		//servlet-context.xml에 저장된 것 처럼
		//접두어(prefix)-/WEB-INF/views/, 접미어(suffix)-.jsp 를 뺀 논리적 이름만 반환
		
		//1. .jsp페이지로 forward(default)
		//return "viewresolver03/ViewResolver";//경로로 접근 불가 반드시 컨트롤러를 거쳐야 한다.
		
		//2. .do로 forward
		//아래처럼 view정보 반환시 접두어 접미어가 붙어서
		///WEB-INF/views/ViewResolver/NotJSP.do.jsp 되고
		//404 에러 발생
		//return "/ViewResolver/NotJSP.do";
		//접두어 접미어 영향 받지 않게 하기
		//return 시 "forward:" 나 "redirect:" 하기 그러면 접두어와 접미어의 영향을 받지 않는다.-당연히 풀경로를 적어야 한다!
		//단, redirect redirect:jsp페이지 경로가 아닌  Context경로 혹은 ModelAndView객체로 반환하면 된다.
		
		//접두어 접미어 영향 받지 않게 하기- 주로 .do로 이동시에 적용한다.
		//[forward로 이동시]
		//1) .jsp 페이지로 forward(default)
		//return "forward:/WEB-INF/views/viewresolver03/ViewResolver.jsp";
		//return "viewresolver03/ViewResolver";// 위는 이 코드와 같다.
		//2. .do로 forward
		//return "forward:/ViewResolver/NotJSP.do";
		
		//[redirect로 이동시] !!!query string으로 저장된 속성이 넘어간다.
		//1) .jsp 페이지로 redirect - /WEB-INF 밑에 있는 jsp파일을 직접 url로 요청한 것과 같다.
		// 사용하면 안된다.
		//return "redirect:/WEB-INF/views/viewresolver03/ViewResolver.jsp"; //404오류
		//서버가 아닌 페이지 요청이므로 에러가 난다.
		
		//2) .do 로 redirect
		return "redirect:/ViewResolver/NotJSP.do";
		
		
		
	}/////execute
	*/
	
	
	
	
	
	
	
	@RequestMapping("/ViewResolver/ViewResolver.do")
	public ModelAndView execute(Model model) {
		//데이터 저장
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		model.addAttribute("message",dateFormat.format(new Date())); //default로 request영역에 저장된다.
		
		return new ModelAndView(new RedirectView("/ViewResolver/NotJSP.do", true));//redirect전용 view
		
		//new ModelAndView(new RedirectView("/ViewResolver/NotJSP.do", true));
		//  ModelAndView는 redirect로 반환할 수 있게 해주는 객체               
		//  RedirectView는 View 상속받음       
	}///////execute
	
	
	
	
	
	
	
	////controller method - execute() method에서 포워드를 받는 메서드
	@RequestMapping("/ViewResolver/NotJSP.do")
	public String notjsp(@RequestParam String message) {
		//redirect로 위에서 넘어오면 쿼리스트링으로 넘어와서 한글이 깨진다.
		System.out.println("파라미터: "+message);
		return "viewresolver03/ViewResolver";//forward
	}
	
	
}//////ViewResolverController
