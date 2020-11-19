package com.kosmo.springapp.basic.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;

@Controller
public class AjaxController {
	//Ajax 미사용시 새로고침이 된다.
	/*
	 * 반환타입이 void: 직접 웹브라우저에 출력 스트림으로 결과값 출력
	 * 반환타입이 String: 디스페텨서블릿에게 뷰 반환, 결과값은 모델에 저장(JSP 로 포워드)
	 */
	@Resource(name="memoService")
	private OneMemoService service;
	
	/*
	@RequestMapping("/Ajax/NoAjax.do")
	public void noajax(@RequestParam Map map, HttpServletResponse resp) throws IOException {
		//1)한글 처리
		resp.setContentType("text/html; charset=UTF-8");
		//2)웹 브라우저에 출력하기위한 출력 스트림 얻기
		PrintWriter out = resp.getWriter();
		//3)비즈니스 로직 호출
		boolean isLogin = service.isLogin(map);
		if(isLogin) {
			out.println("<h2>"+map.get("id")+"님 즐감하세요</h2>");
		}
		else {
			out.println("<script>");
			out.println("alert('아뒤와 비번이 틀려요');");
			out.println("history.back();");
			out.println("</script>");
		}
		out.close();
		
	}///////////noajax
	*/
	
	@RequestMapping("/Ajax/NoAjax.do")
	public String noajax(@RequestParam Map map, Model model) {
		//1)비즈니스(서비스) 로직 호출
		boolean isLogin = service.isLogin(map);
		//2) 데이터 저장
		model.addAttribute("isLogin", isLogin?map.get("id")+"님 반갑습니다.":"회원정보 불일치");
		//3) view 정보 반환
		return "ajax11/Ajax";
	}
	
	
	//Ajax 사용
	/* 반환 타입은 void나 String 사용
	 * 반환타입이 String은 @ResponseBody() 사용--문자열이 브라우저에 출력
	 */
	/*
	//TEXT로 응답
	@RequestMapping("/Ajax/AjaxText.do")
	public void ajaxText(@RequestParam Map map, HttpServletResponse resp) throws IOException {
		//1)한글 처리
		resp.setContentType("text/html; charset=UTF-8");
		//2)웹 브라우저에 출력하기위한 출력 스트림 얻기
		PrintWriter out = resp.getWriter();
		//3)비즈니스 로직 호출
		boolean isLogin = service.isLogin(map);
		//3-1) 응답 - 반드시 print 메서드로 아니면 줄바꿈이 추가된다.
		//out.print(isLogin ? "Y":"N");
		//메시지로 바로 응답
		out.print(isLogin ? map.get("id")+"님 즐감!":"회원 가입해주세요");
		//스트림 닫기
		out.close();
	}
	*/
	//반환 타입 String
	@RequestMapping(value="/Ajax/AjaxText.do", produces="text/html; charset=UTF-8")
	@ResponseBody
	public String ajaxText(@RequestParam Map map) {
		//1)비즈니스(서비스) 로직 호출
		boolean isLogin = service.isLogin(map);
		// y or n
		//return isLogin? "Y" :"N";
		//메시지로 응답
		return isLogin? map.get("id")+"님 Welcome!" :"No Authorized";
	}
	
	
	
	
	//json형태로
	@RequestMapping(value="/Ajax/AjaxJson.do", produces="text/html; charset=UTF-8")
	@ResponseBody
	public String ajaxJson(@RequestParam Map map) {
		//1)비즈니스(서비스) 로직 호출
		boolean isLogin = service.isLogin(map);
		//json 타입 반환 위한  JSONObject 객체 --json.simple 패키지의 객체이다.
		JSONObject json = new JSONObject();
		json.put("isLogin",isLogin?"방가":"다음 기회에");
		System.out.println("json.toString(): "+json.toString());
		System.out.println("json.toJSONString(): "+json.toJSONString());
		return json.toJSONString();
	}///////
	
	
	
	
	
	//json Array형태로
	@RequestMapping(value="/Ajax/AjaxJsonArray.do", produces="text/html; charset=UTF-8")
	@ResponseBody
	public String ajaxJsonArray() {
		
		Map map = new HashMap();
		map.put("start", 1);
		map.put("end", 10);
		List<OneMemoDTO> list = service.selectList(map);
		//[{},{},...] 형태로 반환
		List<Map> collections = new Vector<Map>();
		for(OneMemoDTO dto : list ) {
			Map record = new HashMap();
			record.put("title", dto.getTitle());
			record.put("name", dto.getName());
			record.put("postDate", dto.getPostDate().toString());
			collections.add(record);
		}
		/*
		 * [{"name":"김길동","postDate":2020-11-16,"title":"트와이스는"},{},...] 형태로 반환
		 * 즉 날짜가 문자열이 아님 따라서 날짜는 .toString() 을 해주기
		 */
		
		System.out.println(JSONArray.toJSONString(collections));
		return JSONArray.toJSONString(collections);
	}/////
	
	
	@RequestMapping(value="/Ajax/AjaxCourse.do", produces="text/html; charset=UTF-8")
	@ResponseBody
	public String ajaxCourse(@RequestParam String course) {
		JSONObject json = new JSONObject();
		switch(course) {
		case "java": 
			json.put("j01", "자바");
			json.put("j02", "JSP");
			json.put("j03", "스프링");
			break;
		case "dotnet": 
			json.put("d01", "C#");
			json.put("d02", "ASP.NET");
			json.put("d03", "WPF4");
			break;
		default: 
			json.put("i01", "라즈베리 파이");
			json.put("i02", "파이썬");
		}
		
		return json.toJSONString();
	}///////
	
	
}
