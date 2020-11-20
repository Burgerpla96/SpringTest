package com.kosmo.springapp.basic.validation;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VaildationController {

	
	//방법 1)Spring API 안쓰고 유효성 검증하기... 이 방법이 편하다....
	
	/*
	//controller method
	@RequestMapping("/Validation/ValidationCheck.do")
	public String exec(FormCommand cmd, Model model) {
		
		//※ 중요! ${param.inters}로 출력시 선택한 처음것만 출려된다.
		//따라서 여러개 항목이 있는 것은 영역에 저장하기
		model.addAttribute("inters", Arrays.toString(cmd.getInters())); //체크 유지용
		
		if(!validate(cmd, model)) { //유효성에 맞지 않을때 다시 입력 폼으로 이동
			return "validation09/Validation";
		}
		//model.addAttribute("inters",cmd.getInters());// 출력용
		cmd.setSelf(cmd.getSelf().replace("\r\n", "<br/>"));
		//model.addAttribute("self",cmd.getSelf());
		model.addAttribute("cmd",cmd);
		//뷰정보 반환
		return "validation09/ValidationComplete";
	}
	
	
	
	
	//controller method가 아니다.
	//내가만든 유효성 검증용 메서드
	private boolean validate(FormCommand cmd, Model model) {
		if(cmd.getName().trim().equals("")) {//입력하지 않았을 때
			model.addAttribute("nameError","이름을 입력하세요.");
			return false;
		}
		if(cmd.getYears().trim().length() ==0) {
			model.addAttribute("yearsError","나이를 입력하세요.");
			return false;
		}
		else {
			try {
				Integer.parseInt(cmd.getYears().trim());
			} catch (Exception e) {
				model.addAttribute("yearsError","나이는 숫자만 입력해주세요.");
				return false;
			}
		}
		if(cmd.getGender()==null) {
			model.addAttribute("genderError","성별을 선택하세요.");
			return false;
		}
		if(cmd.getInters()==null) {
			model.addAttribute("intersError","관심사항을 선택하세요.");
			return false;
		}
		else {//2개 이상 체크 조건 걸기
			
			//스트링으로 가져왔을 경우..
//			StringTokenizer tokenizer = new StringTokenizer(cmd.getInters(), ",");
//			if(tokenizer.countTokens() <2) {
//				model.addAttribute("intersError","관심사항을 2개 이상 선택하세요.");
//				return false;
//			}
			
			if(cmd.getInters().length <2) {
				model.addAttribute("intersError","관심사항을 2개 이상 선택하세요.");
				return false;
			}
		}
		if(cmd.getGrade().trim().equals("")) {
			model.addAttribute("gradeError","학력을 선택하세요.");
			return false;
		}
		if(cmd.getSelf().trim().equals("")) {
			model.addAttribute("selfError","자기소개를 입력하세요.");
			return false;
		}

		return true;
	}///////////validate
	*/
	
	
	
	//방법2] spring API 사용
	
	//Validator주입
	@Resource(name="validator")
	private FormValidator validator;
	
	
	
	@RequestMapping("/Validation/ValidationCheck.do")
	//매개변수 순서 지키기 FormCommand 다음에 BindingResult!!
	public String exec(FormCommand cmd, BindingResult errors,Model model) {
		//내가 만든 validator 클래스의 validate()호출
		//validate() 메서드 첫 매개변수에 유효성 검증 해달라고 커맨드 객체 넣어주고
		//두번째로 에러정보 담아 달라고 Errors 타입 전달
		//System.out.println(cmd);
		validator.validate(cmd, errors);
		//에러난지 판단해서 돌리기
		if(errors.hasErrors()) {
			model.addAttribute("inters", Arrays.toString(cmd.getInters())); //체크 유지용
			//뷰정보 반환
			return "validation09/Validation";
		}
		
		cmd.setSelf(cmd.getSelf().replace("\r\n", "<br/>"));
		model.addAttribute("cmd",cmd);
		//ResourceBundleMessageSource 로 메세지 출력
		//뷰정보 반환
		return "validation09/ValidationComplete";
	}/////////////exec
	
	
	
	
	
}
