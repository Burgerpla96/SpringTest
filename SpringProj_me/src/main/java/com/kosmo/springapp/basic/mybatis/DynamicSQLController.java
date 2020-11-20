package com.kosmo.springapp.basic.mybatis;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/MyBatis")
@Controller
public class DynamicSQLController {

	@Resource(name="dynamic")
	private DynamicSQLDao dynamic;
	@RequestMapping("/If1.do")
	public String if1(Model model,@RequestParam Map map) {
		List list=dynamic.if1(map);
		model.addAttribute("message","검색된 총 글 수:"+ list.size());
		return "dynamicsql10/DynamicSQL";
	}//////////////
	
	@RequestMapping("/If2.do")
	public String if2(Model model,@RequestParam Map map) {
		List list=dynamic.if2(map);
		model.addAttribute("message","검색된 총 글 수:"+ list.size());
		return "dynamicsql10/DynamicSQL";
	}//////////////
	@RequestMapping("/choose.do")
	public String choose(Model model,@RequestParam Map map) {
		List list=dynamic.choose(map);
		model.addAttribute("message","검색된 총 글 수:"+ list.size());
		return "dynamicsql10/DynamicSQL";
	}//////////////
	@RequestMapping("/where.do")
	public String where(Model model,@RequestParam Map map) {
		List list=dynamic.where(map);
		model.addAttribute("message","검색된 총 글 수:"+ list.size());
		return "dynamicsql10/DynamicSQL";
	}//////////////
	@RequestMapping("/trim1.do")
	public String trim1(Model model,@RequestParam Map map) {
		List list=dynamic.trim1(map);
		model.addAttribute("message","검색된 총 글 수:"+ list.size());
		return "dynamicsql10/DynamicSQL";
	}//////////////
	@RequestMapping("/trim2.do")
	public String trim2(Model model,@RequestParam Map map) {
		int affected=dynamic.trim2(map);
		model.addAttribute("message","업데이트 행수:"+ affected);
		return "dynamicsql10/DynamicSQL";
	}//////////////
	@RequestMapping("/set.do")
	public String set(Model model,@RequestParam Map map) {
		int affected=dynamic.set(map);
		model.addAttribute("message","업데이트 행수:"+ affected);
		return "dynamicsql10/DynamicSQL";
	}//////////////
	@RequestMapping("/foreach.do")
	public String foreach(Model model) {
		List lists = Arrays.asList(1,2,3);
		//1.List인 경우
		//lists=dynamic.foreach(lists);
		
		//2.Map인 경우
		Map map = new HashMap();
		map.put("keyno", lists);
		lists=dynamic.foreach(map);
		
		model.addAttribute("message","검색된 총 글 수:"+ lists.size());
		return "dynamicsql10/DynamicSQL";
	}//////////////
}
