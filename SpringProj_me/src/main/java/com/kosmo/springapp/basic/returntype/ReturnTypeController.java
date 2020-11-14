package com.kosmo.springapp.basic.returntype;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

//controller class
@Controller
public class ReturnTypeController {
	//controller method 반환타입- ModelAndView
	@RequestMapping("/ReturnType/ModelAndView.do")
	public ModelAndView modelandview(@RequestParam Map map, Model model) {
		//HttpServletRequest 를 사용하지 않고, @RequestParam 쓰는 것이 Spring철학에 맞다.(프레임 워크의 이용)
		//여기서 Map은 파라미터로 받은게 저장된어 있다. - model에 저장 (request영역에 저장됨)
		
		
		//방법 1) - ModelAndView에 view 정보만 저장!!
		//data 저장] -Model에 데이터 저장
		//model.addAllAttributes(map);/////model에 저장 (request영역에 저장됨!!!)
		//model.addAttribute("message", String.format("[파라미터:%s]", map.get("returnType")));
		//view 정보 반환] - ModelAndView 사용
		//return new ModelAndView("returntype04/ReturnType");
		
		//방법 2) - ModelAndView에 data 저장 -- 메서드에 파라미터 변수로 Model이 필요없다.
		//data 저장] ModelAndView에 data 저장
		ModelAndView mav = new ModelAndView();
		mav.addAllObjects(map);
		mav.addObject("message", String.format("[파라미터:%s]", map.get("returnType")));
		//view 정보 저장] - ModelAndView 사용
		// String 저장시에 setViewName()
		//mav.setViewName("returntype04/ReturnType"); 
		// setView로 InternalResourceView 생성자 이용
		mav.setView(new InternalResourceView("/WEB-INF/views/returntype04/ReturnType.jsp"));
		return mav;
		
	}///////modelandview
	
	
	
	//controller method 반환타입- String --view 정보만 반환
	@RequestMapping("/ReturnType/String.do")
	public String string(@RequestParam String returnType, Map map) {
		//파라미터가 String으로 저장되어 온다.
		//map에는 request에 저장 할것을 넣는다.
		//데이터 저장을 map에 한다. -- 위와는 다르다.!!! 중요 주의!
		
		//데이터 map에 저장]
		map.put("message", String.format("[파라미터:%s]", returnType));
		map.put("returnType", returnType);
		//view 정보 반환]
		return "returntype04/ReturnType";
		
	}////////////////string
	
	
	@RequestMapping("/ReturnType/Void.do")
	public void noreturn(@RequestParam String returnType, HttpServletResponse resp) throws IOException {
		/*
		 * -Ajax나 Rest API 구현시 주로 사용
		 * -DispatcherServlet에게 모델 및 뷰정보 전달 안함
		 *  즉 ViewResolver를 거치지 않음
		 * -웹브라우저에 바로 출력시 사용
		 * 즉 Http응답 바디에 데이터 출력시 사용
		 * 
		 */
		//응덥헤더 컨텐트 타입 설정 - 한글 안 깨지도록
		resp.setCharacterEncoding("charset=UTF-8");
		//웹 브라우저에 출력하기 위한 출력 스트림 얻기
		PrintWriter out = resp.getWriter();//예외 던지면 알아서 처리해준다.
		out.println("<fieldset>");
		out.println("<legend> 반환타입: "+returnType+"</legend>");
		out.println("<h2>웹 브라우저로 직접 출력합니다.</h2>");
		out.println("</fieldset>");
		//스트림 닫기
		out.close();
		
	}//////////////noreturn
	
	
	
}
