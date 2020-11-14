package com.kosmo.springapp.basic.annotation;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestMappingController {
	//@RequestMapping는 메서드 앞이 아닌 class 앞에 붙여도 된다. 단, 차이가 있다.
	
	/* 
	 * @RequestMapping("/요청 URL")
	 * -주로 요청을 처리하는 메서드 앞에 단다.
	 * -컨텍스트 루트를 제외한 /로 시작하는 요청 URL
	 * -get/post 방식 둘다 처리가능
	 */
	/*
	@RequestMapping("/Annotation/RequestMappingBoth.do")
	public String both(HttpServletRequest req) {
		//data저장
		String loginInfo = 
				String.format("[아이디:%s, 비번:%s, 요청방식:%s]", 
						req.getParameter("UserID"),
						req.getParameter("UserPWD"),
						req.getMethod());
		//req 영역에 저장
		req.setAttribute("loginInfo", loginInfo);
		//뷰정보 반환
		return "annotation06/Annotation";
	}////////both
	*/
	
	
	
	/*
	 * @RequestMapping(value="/요청명", method=전송방식지정)
	 * -get/post 둘중 하나만 처리 가능
	 */
	/*
	@RequestMapping(value="Annotation/RequestMappingOne.do", method=RequestMethod.POST)
	public String one(HttpServletRequest req) {
		//데이터 저장
		String loginInfo = 
				String.format("[아이디:%s, 비번:%s, 요청방식:%s]", 
						req.getParameter("user"),
						req.getParameter("pass"),
						req.getMethod());
		//req 영역에 저장
		req.setAttribute("loginInfo", loginInfo);
		//뷰정보 반환
		return "annotation06/Annotation";
	}/////////////one
	*/
	
	
	
	/*
	 * @RequestMapping(value={"요청URL1","요청URL2",...}) 혹은
	 * @RequestMapping({"요청URL1","요청URL2",...})
	 * 여러 요청을 하나의 컨트롤러 메서드가 처리한다.
	 */
	/*
	@RequestMapping(value={"/Annotation/RequestMappingBoth.do","Annotation/RequestMappingOne.do"}, method=RequestMethod.POST)
	public String multi(@RequestParam Map map, Model model) {
		//데이터 저장
		String id = map.get("UserID") != null ?  map.get("UserID").toString() : map.get("user").toString(); 
		String pwd = map.get("UserPWD") != null ?  map.get("UserPWD").toString() : map.get("pass").toString(); 
		model.addAttribute("loginInfo", String.format("[아이디:%s, 비번:%s]", id, pwd));
		//뷰 정보 반환
		return "annotation06/Annotation";
	}
	*/
	
	
	
	/*
	 * @PathVariable로 URL패턴을 변수 형태로 치환
	 * 반드시 @PathVariable의 변수명과 {변수명}을 일치해야 한다.
	 * 여러 요청을 동시에 처리할때 사용 혹은 REST API시 사용
	 */
	@RequestMapping("Annotation/{path}")
	public String path(@PathVariable String path, @RequestParam Map<String,String> map, Model model) {
		String id,pwd;
		switch (path) {
		case "RequestMappingBoth":
			id = map.get("UserID");
			pwd = map.get("UserPWD");
			break;
		default:
			id = map.get("user");
			pwd = map.get("pass");
			break;
		}
		//데이터 영역에 저장
		model.addAttribute("loginInfo", String.format("[아이디:%s, 비번:%s]", id, pwd));
		//view정보 반환
		return "annotation06/Annotation";
	}
	
	
	
	
	
	
}
