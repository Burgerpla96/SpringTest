package com.kosmo.springapp.basic.handlermapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class SimpleUrlSecondController extends AbstractController{
	
	//컨트롤러 메서드
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//맵핑위치, 키값, 벨류
		return new ModelAndView("handlermapping01/HandlerMapping","message","[SimpleUrlSecond.do]") ;
	}/////////////handleRequestInternal
}
