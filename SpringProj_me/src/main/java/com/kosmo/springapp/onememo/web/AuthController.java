package com.kosmo.springapp.onememo.web;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosmo.springapp.onememo.service.OneMemoService;

@Controller
@RequestMapping("/OneMemo/Auth/")
public class AuthController {
	//서비스 주입]
	@Resource(name="memoService")
	private OneMemoService memoService;
	
	//로그인 폼으로 이동]
	@RequestMapping("Login.do")
	public String login() {
		return "onememo10/member/Login.tiles";
	}/////////////////login
	//스프링씨큐리티 적용시 로그인처리 /로그아웃처리 주석처리
	
	
	//로그인 여부 판단
	@RequestMapping(value="IsLogin.do", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String isLogin(Authentication auth) {
		//만약 인증이 안되었다면 auth는 null
		JSONObject json = new JSONObject();
		if(auth == null) {
			json.put("isLogin", "NO");
			return json.toJSONString();
		}
		json.put("isLogin", "YES");
		return json.toJSONString();
		
	}/////////////////isLogin
	
	
	/*
	//로그인 처리]
	@RequestMapping("LoginProcess.do")
	public String process(HttpSession session,@RequestParam Map map,Model model) {
		
		//서비스 호출]
		boolean flag=memoService.isLogin(map);
		
		if(flag)//회원-세션 영역에 데이타 저장
			session.setAttribute("id", map.get("id"));
		else //비회원이거나 아이디가 틀린경우
			model.addAttribute("NotMember", "아뒤와 비번이 틀려요");
		//뷰정보 번환]
		return "onememo10/member/Login.tiles";
			
	}//////////process
	//로그아웃 처리]
	@RequestMapping("Logout.do")
	public String logout(HttpSession session) {
		//로그아웃 처리-세션영역 데이타 삭제
		session.invalidate();
		//뷰정보 번환]
		return "onememo10/member/Login.tiles";
	}/////////////logout
	*/
}
