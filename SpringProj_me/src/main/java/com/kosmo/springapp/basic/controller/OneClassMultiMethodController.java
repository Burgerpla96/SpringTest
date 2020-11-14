package com.kosmo.springapp.basic.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

//하나의 클래스로 여러 요청 처리(여러개의 컨트롤러 메서드 사용)

//컨트롤러 클래스
@Controller
public class OneClassMultiMethodController {
	
	//컨트롤러 메서드
	@RequestMapping("/Controller/OneClass/List.do")
	public String list(ModelMap model) {
		//데이터 저장
		model.addAttribute("message","[목록 요청입니다.]");
		//디스패쳐 서블릿에게 뷰정보 반환 => 기본이 forward임..  
		return "controller02/Controller";
	}//////list
	
	//컨트롤러 메서드
	@RequestMapping("/Controller/OneClass/Edit.do")
	public String edit(Model model) {
		//데이터 저장
		model.addAttribute("message","[수정 요청입니다.]");
		//디스패쳐 서블릿에게 뷰정보 봔환
		return "controller02/Controller";
	}//////edit
	
	//컨트롤러 메서드
	@RequestMapping("/Controller/OneClass/Delete.do")
	public String delete(Map model) {
		//데이터 저장
		model.put("message","[삭제 요청입니다.]");
		//디스패쳐 서블릿에게 뷰정보 봔환
		return "controller02/Controller";
	}//////delete
	
	
	//컨트롤러 메서드
	@RequestMapping("/Controller/OneClass/View.do")
	public String view(Map model) {
		//데이터 저장
		model.put("message","[상세보기 요청입니다.]");
		//디스패쳐 서블릿에게 뷰정보 봔환
		return "controller02/Controller";
	}//////view
	
	
}///////class OneClassMultiMethodController
