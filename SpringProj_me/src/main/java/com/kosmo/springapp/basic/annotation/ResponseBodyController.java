package com.kosmo.springapp.basic.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResponseBodyController {
	
	//웹 브라우저로 직접 출력하기 //controller method의 반환 타입할 때 하였던 방식!!!!@@@ 다시 보기
	//방법1] 반환타입은 void, 매개변수는 서블릿 API(HttpServletResponse)사용- @ResponseBody 미사용
	/*
	@RequestMapping("/Annotation/ResponseBody.do")
	public void exec(HttpServletResponse resp) throws IOException {
		//한글 처리
		resp.setCharacterEncoding("text/html; charset=UTF-8");
		//브라우저로 출력하기 위한 출력 스트림
		PrintWriter out = resp.getWriter();
		out.println("<h2>반환타입: void, 서블릿 API 사용: HttpServletResponse</h2>");
		//스트림 닫기
		out.close();
	}/////////exec
	*/
	
	
	//방법2] 반환타입 String, 서블릿 API 미사용,  @ResponseBody 사용
	@RequestMapping(value = "/Annotation/ResponseBody.do", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String exec() {
		return "<h2>반환타입: String, 서블릿 API 미사용, @ResponseBody 사용</h2>";
		//응답 바디에 반환 스트링이 들어간다.
	}
	
	
	
	
}
