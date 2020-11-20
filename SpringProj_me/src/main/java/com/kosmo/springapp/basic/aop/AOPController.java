package com.kosmo.springapp.basic.aop;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AOPController {
	
	//대상 주입
	@Resource(name="targetObject")
	private TargetClass target;
	
	@RequestMapping("/AOP/AOP.do")
	public String execute(Model model) {
		//대상..
		long total = target.getTotal();
		model.addAttribute("total",total);
		return "aop14/AOP";
	}
	
	
}
