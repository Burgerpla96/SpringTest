package com.kosmo.springapp.basic.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//controller class
@Controller
public class OneClassOneMethodController {
	
	//controller method -parameter로 구분
	@RequestMapping({
		"/Controller/OneMethod/List.do",
		"/Controller/OneMethod/Edit.do",
		"/Controller/OneMethod/Delete.do",
		"/Controller/OneMethod/View.do"})
	public String parameter(@RequestParam int paramvar, Map map) {//주석으로 받고 타입형 설정
		//데이터 저장
		switch(paramvar) {
		case 1: map.put("message","목록요청");break;
		case 2: map.put("message","수정요청");break;
		case 3: map.put("message","삭제요청");break;
		default: map.put("message","상세보기 요청"); 
		}//model이나 map으로 저장하면 dispatcher로 전달된다.
		//디스패쳐 서블릿에게 뷰정보 반환 => 기본이 forward임..  
		return "controller02/Controller";
		
	}//////////////parameter
	
	
	//컨트롤러 메서드 - URL path로 구분 요청
	@RequestMapping("/Controller/OneMethodNoParam/{path}")
	public String noparam(@PathVariable String path, Map map) {
		//{}안에 변수를 넣으면 된다.
		//System.out.println(path);//path는 List 나 Delete 이렇게 나온다.
		
		
		//데이터 저장
		switch(path.toUpperCase()) {
		case "LIST": map.put("message","목록요청(URL path로 구분)");break;
		case "EDIT": map.put("message","수정요청(URL path로 구분)");break;
		case "DELETE": map.put("message","삭제요청(URL path로 구분)");break;
		default: map.put("message","상세보기 요청(URL path로 구분)"); 
		}
		
		//디스패쳐 서블릿에게 뷰정보 반환 => 기본이 forward임
		return "controller02/Controller";
	}////noparam
	
	
}
