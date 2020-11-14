package com.kosmo.springapp.basic.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


//org.springframework.validation.Validator를 상속받아야 검증자가 된다.
/*
 * Validator interface를 상속받으면 특정 DTO객체(커멘드 객체)의 유효성 검증을
 * 할 수 있는 자격을 갖춘 Validator 클래스 역할을 한다.
 * 
 * 모든 커멘드 객체를 검사하는 것이 아닌 설정된 객체만 검사한다.
 */
public class FormValidator implements Validator{

	/*
	 * supports()
	 * 매개변수에 전달된 커멘드 객체가 유효성 검증을 지원 할 수 있는 커멘드 객체인지 아닌지 판단하는 메서드
	 * 만약 이 메서드를 통과 못 하면  validate() 호출 불가.
	 * 지원이 가능하면 validate() 호출가능.
	 */
	
	@Override
	public boolean supports(Class<?> command) {
		/*
		 * 매개변수로 받은 command가 FormCommand 타입인지 
		 * 혹은 FormCommand의 자식타입인지 판단하는 메서드
		 * supports() method는 프레임워크가 호출한다.
		 * 매개변수인 command는 컨트롤러 클래스가 매개변수로 받은 커맨드 객체를 넘겨줌
s		 */
		
		//isAssignableFrom(): command가 FormCommand 타입이거나 FormCommand 상속받은 자식 타입이면 true 아님 false
		//return FormCommand.class.isAssignableFrom(command);
		
		//equals(): command가 FormCommand 타입이면 true 아님 false
		return FormCommand.class.equals(command);
	}////////////supports
	
	
	/*
	 * validate()
	 * supports()에서 true일 경우만 실행
	 * 즉 유효성 검사를 하고자 하는 커맨드 객체가 맞는 경우에만 실행된다.
	 * 
	 * 
	 * -개발자가 컨트롤러 메서드에서 호출
	 */
	
	@Override
	public void validate(Object command, Errors errors) {
		/*
		 * 오류시
		 * Errors타입에 에러정보 저장
		 * rejectValue("FormCommand의 속성명","에러코드");
		 * 에러코드(중복 불가능!!!)는 임의로... 
		 */
		FormCommand cmd = (FormCommand)command;
		if(cmd.getName().trim().equals("")) {//입력하지 않았을 때
			errors.rejectValue("name", "nameError");
		}
		if(cmd.getYears().trim().length() ==0) {
			errors.rejectValue("years", "yearsError");
		}
		else {
			try {
				Integer.parseInt(cmd.getYears().trim());
			} catch (Exception e) {
				errors.rejectValue("years", "yearsNotNumber");
			}
		}
		if(cmd.getGender()==null) {
			errors.rejectValue("gender", "genderError");
		}
		if(cmd.getInters()==null) {
			errors.rejectValue("inters", "intersError");
		}
		else {//2개 이상 체크 조건 걸기
			
			//스트링으로 가져왔을 경우..
//			StringTokenizer tokenizer = new StringTokenizer(cmd.getInters(), ",");
//			if(tokenizer.countTokens() <2) {
//				model.addAttribute("intersError","관심사항을 2개 이상 선택하세요.");
//				return false;
//			}
			if(cmd.getInters().length <2) {
				errors.rejectValue("inters", "intersLackError");
			}
		}
		if(cmd.getGrade().trim().equals("")) {
			errors.rejectValue("grade", "gradeError");
		}
		if(cmd.getSelf().trim().equals("")) {
			errors.rejectValue("self", "selfError");
		}
		
	}////////////validate

}
