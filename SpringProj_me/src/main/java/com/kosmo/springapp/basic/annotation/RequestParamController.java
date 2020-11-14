package com.kosmo.springapp.basic.annotation;

import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Annotation")//앞으로 '/Annotation'URL은 안쳐도 된다.
public class RequestParamController {
	//HttpServletRequest 대신 @RequestParam 사용
	
	//
	//required 속성은 디폴트가 true이다.
	//만약 파라미터명으로 전달이 안되면 에러(400에러)
	//required=false로 주면 해당 파라미터명으로 전달이 안된다해 도 에러가 나지 않는다.
	
	
	
	
	//@RequestParam 사용시, 나이가 숫자가 아닌 값을 입력시 오류처리
	/*
	@ExceptionHandler({Exception.class})
	public String error(Exception e) {//에러 내용을 파라미터로 전달
		//return "annotation06/Annotation.jsp?error="+e.getMessage();
		//혹은
		return "forward:/WEB-INF/views/annotation06/Annotation.jsp?error="+e.getMessage();
	}
	*/
	/*
	@ExceptionHandler({Exception.class})
	public String error(Exception e, Model model) {//에러 내용을 리퀘스트 영역에 전달
		model.addAttribute("errorNumber","나이는 숫자만...!!!"+e.getMessage());
		return "annotation06/Annotation";
	}
	*/
	
	
	@RequestMapping("/RequestParamEqual.do")//class위의 주소와 합쳐진다.
	public String equals(@RequestParam String name, @RequestParam int years, Model model) {
	/*
	public String equals(HttpServletRequest req) {
		
		try {
			//파라미터 받기 -- HttpServletRequest 사용
			String name = req.getParameter("name");
			int age = Integer.parseInt(req.getParameter("years"));
			//리퀘스트 영역에 저장
			req.setAttribute("name", name);
			req.setAttribute("years", age+10);
		} catch (NumberFormatException e) {
			req.setAttribute("errorNumber", "나이는 숫자만...");
		}
	*/
		
		//@RequestParam으로 받을 때는 위의 getParameter() 불필요, 형변환도 불필요
		//데이터 저장
		model.addAttribute("name",name);
		model.addAttribute("years",years+10);
		
		//뷰정보 반환
		return "annotation06/Annotation";
	}

	
	
	//컨트롤러 메서드]- 파라미터명과 변수명이 불일치시
	@RequestMapping("/RequestParamDiff.do")
	public String diff(Model model, @RequestParam(required = false, value = "nick2")
		String name, @RequestParam(required = false, value = "age") int years) {
		
		//파라미터명과 변수명이 불일치시 -  @RequestParam("파라미터명")으로 변수를 설정해야 함.
		model.addAttribute("name", name);
		model.addAttribute("years",years+10);
		return "annotation06/Annotation";
	}////////diff
	
	
	
	//컨트롤러 메서드] - Map 으로 파라미터 받기
	//단, 체크박스는 여러개 선택해도 하나만 받는다.
	@RequestMapping("/RequestParamMap.do")
	public String map(@RequestParam Map map, ModelMap model, @RequestParam String[] inters) {
		//form의 파라미터명이 key값이 되고, 입력이나 선택한 값이 value값이 된다.
		//단, 체크박스류는 //무조건 첫번째 선택한 것만 저장된다.
		
		//기존 데이터에 저장된 checked box 내용수정
		map.put("inters", Arrays.toString(inters));
		//데이터 저장
		model.addAllAttributes(map);
		//view 정보 반환
		return "annotation06/Annotation";
	}
	
	
	
	
	
	
	
	

}
