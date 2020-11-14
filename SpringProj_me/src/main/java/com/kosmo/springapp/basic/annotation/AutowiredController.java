package com.kosmo.springapp.basic.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @Autowired ----type -> id -> Qualifier 순으로 검색한다.
 * -속성, setter에 붙일 수 있다.
 * -의존관계 자동설정(type기반!!! 기억, 중요)
 * - 만약 주입하고자 하는 객체가 없다면 와이어링을 못함으로 예외 발생,
 *  단, @Autowired의 required속성을 false로 지정하면 예외발생X
 * -설정파일에 여러개를 등록후 (ID 속성 미부여) 주입시에는 예외발생(어느 객체를 주입할지 몰라서)
 * -설정파일에 여러개를 등록후, ID 속성 부여시, id와 일치하는 controller의 맴버변수를 찾아 주입시켜준다.
 * -주입받을 콘트롤러의 타입속성이 여러개인 경우, (같은 객체가 변수명만 다르게 여러개) 같은 것을 주입시킨다.(주소가 같다.)
 * -따라서 여러개 등록시에는 id속성 주거나 한 type에 하나만 bean으로 등록하는게 좋다. 
 *  
 *  ------------------------비메이븐의 내용을 포함하고 있음.
	-생성자,필드,메서드에 적용가능
	-setxxx()의 형식이 아니어도 적용가능
	-타입을 이용해 자동적으로 프로퍼티 값을 
	 설정하므로 해당 타입의 빈 객체가 존재하지 않거나 또는 빈 객체가 두 개 이상 존재할 경우 예외를 발생시키게 된다.
	단,@Autowired 어노테이션에 required 속성의 값을 false로 지정해 주면
	해당 타입의 빈 객체가 존재하지 않더라도 예외를 발생하지 않는다.
	-사용시 설정파일에 AutowiredAnnotationBeanPostProcessor클래스를
	 빈 객체로 등록후 사용
	 혹은 <context:annotaion-config/>만 등록하여 주어도 자동으로 
	 @Autowired를 사용할 수 있다

	-@Autowired적용시에는 컨트롤러 클래스에 setter계열메서드 생력하고
	 설정파일에서 <property>생략
	주의:type으로 와이어링 하기때문에 속성에 동일한 자료형이 존재하는 경우
	       설정파일에서 생성된 bean객체의 같은 주소가 와이어링된다
	       즉 다른 주소를 갖는 객체가 아니다
	       
	설정파일에서 클래스 해주는게 wiring해주는것
	설정파일에서 주입받을 클래스를 p접두어나, property tag로 class에서 만든 속성을(setter있음) 지시했어야 한다.
	Autowired는 이렇게 주입할 필요가 없다. Annotation만 하면된다.(setter와, servlet-context.xml에서 속성 지시 불필요)
	
	 
 */





@Controller
@RequestMapping("/Annotation")
public class AutowiredController {
	
	//주로 속성에 붙이면 된다.
	//굳이 setter까지 만들 필요는 없다. - 받은 값을 가공할 때는 setter 이용
	@Autowired(required = false)
	@Qualifier("fCommand")
	private Command fCommand;
	@Autowired
	@Qualifier("sCommand")
	private Command sCommand;
	//bean에 주입될게 두 개면 에러(servlet-context.xml에서)
	//UnsatisfiedDependencyException 
	//NoUniqueBeanDefinitionException --bean에 주입될 게 하나여야함
	//id를 주면 해결된다.
	
	
	@RequestMapping("/Autowired.do")
	public String execute(Model model) {
		model.addAttribute("message",String.format("<br/>fCommand: %s<br/> sCommand: %s", fCommand, sCommand));
		return "annotation06/Annotation"; 
	}
	
	
	
	///Annotation/Resource.do"
}
