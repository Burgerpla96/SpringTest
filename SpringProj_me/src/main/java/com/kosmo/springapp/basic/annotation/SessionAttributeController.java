package com.kosmo.springapp.basic.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


/* 
 * @SessionAttributes() 
 * 
 * -서블릿API(HttpSession)를 사용하지 않고 세션처리를 위한 어노테이션
 * -클래스 앞에 붙인다.
 * -세션변수(세션 영역에 저장한 속성명)에 값을 저장하려면 메소드의 매개변수 중 모델계열(Model,Map,ModelMap)
 * 을 추가하여 add("세션변수",값)으로 저장하면 그 이름으로 세션 영역에도 저장한다.(request 영역에도 저장된다.) 
 * 		add계열로 로그인 처리
 * -세션 변수명이 여러개면 {}안에 변수명 담아 넣어준다. -> @SessionAttributes({"세션변수1","세션변수2",...}) 
 * 
 * -세션영역에 값을 읽어 올때!
 * 컨트롤러메서드(@ModelAttribute(value="세션변수면") String 세션값 받을 변수명) 사용 --세션에 변수 없으면 오류난다.
 * 
 * -세션해제(로그아웃 처리)
 * 컨트롤러메서드(SessionStatus session) {
 * 		session.setComplete();
 * }
 * 
 */

//방법1 - 커맨드 객체 미사용으로 아이디/비번 파라미터 Map으로 받음
//@SessionAttributes({"user","pass"})

//방법2 - 커맨드 객체 사용
@SessionAttributes(types = LoginCommand.class)

@Controller
@RequestMapping("/Annotation")
public class SessionAttributeController {
	
	//우선 servlet API(Application Programming Interface-프로그램이나 언어에서 사용할 수 있게 만든 기능) 이용(HttpSession)
	/*
	@RequestMapping("/SessionAttributeLogin.do")
	public String login(HttpSession session, @RequestParam Map map, Model model) {
		//회원 정보 판단
		if("KIM".equalsIgnoreCase((String)map.get("user")) && "1234".equals(map.get("pass"))) {
			//로그인 처리
			session.setAttribute("user", map.get("user"));
		}
		else {
			model.addAttribute("errorMessage","아이디와 비번 불일치");
		}
		//view 정보 반환
		return "annotation06/Annotation"; 
	}//////////////login
	
	
	@RequestMapping("/SessionAttributeLogout.do")
	public String logout(HttpSession session) {
		//session 영역 비우기
		session.invalidate();
		//view 정보 반환
		return "annotation06/Annotation"; 
	}//////////logout
	
	
	//로그인 여부 판단.
	@RequestMapping("/isLogin.do")
	public String isLogin(HttpSession session, Model model) {
		//로그인 여부 판단- 세션영역에 존재 유무로 판단후 데이터 저장
		model.addAttribute("isLoginMessage",session.getAttribute("user")==null?"로그인하세요.":"로그인 되었습니다.");
		//view 정보 반환
		return "annotation06/Annotation"; 
	}/////////isLogin
	*/
	
	
	
	
	
	//@SessionAttribute 사용
	//[1] session영역에 저장할 속성명을 문자열로 지정시 -- @SessionAttributes({"user","pass"})
	/*
	//로그인
	@RequestMapping("/SessionAttributeLogin.do")
	public String login(@RequestParam Map map, Model model) {
		//회원 정보 판단
		if("KIM".equalsIgnoreCase((String)map.get("user")) && "1234".equals(map.get("pass"))) {
			//@SessionAttribute를 사용하여 Model 에 저장시 session영역에도 (request영역뿐만 아니라) 저장된다.
			//회원이면 데이터를 session영역에 저장.
			model.addAllAttributes(map);			
		}
		else {
			model.addAttribute("errorMessage","아이디와 비번 불일치");
		}
		//view 정보 반환
		return "annotation06/Annotation"; 
	}////////////login
		
	
	//로그아웃
	@RequestMapping("/SessionAttributeLogout.do")
	public String logout(SessionStatus session) {
		//로그아웃 처리- 세션영역에 저장된 속성 삭제
		session.setComplete();
		//view 정보 반환
		return "annotation06/Annotation"; 
	}////////////logout
	
	
	//컨트롤러 메서드의 매개변수로 @ModelAttribute(value = "user") String id 지정시
	//session영역에 user가 없으면 오류난다.
	//
	//해결책
	// 방법 1. 서블릿 API(HttpSession)으로 작업한다.
	// 방법 2. bean설정 파일에서 예외처리한다. -try catch 불가. 방법3 보다 우선순위가 높다.
	// 방법 3. @ExceptionHandler 사용
	
	//user가 session에 없을 경우 예외처리
	@ExceptionHandler({Exception.class})
	public String error(Exception e, Model model) {//에러 내용을 리퀘스트 영역에 전달
		model.addAttribute("isLoginMessage","로그인하세요!!!"+e.getMessage());
		return "annotation06/Annotation";
	}
	//로그인 여부 판단   //session 영역에 있는 값을 읽어와야 한다.
	@RequestMapping("/isLogin.do")
	public String isLogin(@ModelAttribute(value = "user") String id, Model model) {//변수명이 같아야 가져올 수 있다. //저장 없으면 에러!!!!!
		//Session영역에 속성이 저장되었을 때만 메서드 안으로 들어온다. //즉 로그인이 될때만 들어옴
		model.addAttribute("isLoginMessage",id+"님 로그인 되었어요!!!");
		//view 정보 반환
		return "annotation06/Annotation"; 
	}////////////isLogin
	*/
	
	
	
	
	
	
	//@SessionAttribute 사용
	//[2] Command(DTO 객체) 객체 사용
	//이 방식은 request영역에 저장 시키지 않는다!!!
	
	//로그인
	@RequestMapping("/SessionAttributeLogin.do")
	public String login(LoginCommand cmd, Model model, SessionStatus session) {
		//회원여부 판단
		//키값은 자동으로 소문자로 시작하는 커맨드 클래스 명이 키값(loginCommand)이 된다.!!!
		//주의!!!!!! - 매개변수의 LoginCommand 객체가 무조건 (회원이든 아니든) 자동으로 session 영역에 저장한다.
		// 단, 빈 설정 파일에 <annotation-driven/> tag 추가해야한다.- 이미 해놨음
		//ex) - session.setAttribute("loginCommand",cmd)식으로 저장된다.
		if(!("KIM".equalsIgnoreCase((String)cmd.getUser()) && "1234".equals(cmd.getPass()))) {//회원이 아닌경우,
			model.addAttribute("errorMessage","회원이 아닙니다.");
			//회원이 아니여도 저장이 되므로 먼저 회원이 아닌지!!! 판단하고 session을 지워야 한다.!!!
			session.setComplete();
		}
		//회원은 자동 저장..
		//view 정보 반환
		return "annotation06/Annotation"; 
	}/////////////////login
	
	
	//로그아웃
	@RequestMapping("/SessionAttributeLogout.do")
	public String logout(SessionStatus session) {
		//로그아웃 처리- 세션영역에 저장된 속성 삭제
		session.setComplete();
		//view 정보 반환
		return "annotation06/Annotation"; 
	}////////////logout
	
	
	
	//user가 session에 없을 경우 예외처리
	@ExceptionHandler({Exception.class})
	public String error(Exception e, Model model) {//에러 내용을 리퀘스트 영역에 전달
		model.addAttribute("isLoginMessage","로그인하세요!!!"+e.getMessage());
		return "annotation06/Annotation";
	}
	
	//로그인 여부 판단   //session 영역에 있는 값을 읽어와야 한다.
	@RequestMapping("/isLogin.do")
	public String isLogin(@ModelAttribute(value = "loginCommand") LoginCommand cmd, Model model) {//변수명이 같아야 가져올 수 있다. //저장 없으면 에러!!!!!
		//Session영역에 속성이 저장되었을 때만 메서드 안으로 들어온다. //즉 로그인이 될때만 들어옴
		model.addAttribute("isLoginMessage", cmd.getUser()+"님 로그인 되었어요!!!");
		//view 정보 반환
		return "annotation06/Annotation"; 
	}////////////isLogin
	
	
	
	
	
}
