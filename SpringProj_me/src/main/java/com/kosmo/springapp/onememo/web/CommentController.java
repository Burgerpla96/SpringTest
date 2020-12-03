package com.kosmo.springapp.onememo.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kosmo.springapp.onememo.service.MemoCommentService;

@SessionAttributes("id")//시큐리티 사용시 주석처리
@Controller
@RequestMapping("/OneMemo/Comment/")
public class CommentController {
	//서비스 주입
	@Resource(name = "commentService")
	private MemoCommentService commentService;
	
	
	//코멘트 입력처리
	@RequestMapping(value = "Write.do",produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String write(
			//@ModelAttribute("id") String id, 
			Authentication auth,
			@RequestParam Map map) {
		//서비스 호출
		//map.put("id", id);//한글 댓글 작성자의 아이디 넘겨주기0
		map.put("id", ((UserDetails)auth.getPrincipal()).getUsername());
		commentService.insert(map);
		return map.get("no").toString();//원본 글의 번호 반환
	}//////////////write
	
	
	//특정 글 번호에 대한 코멘트 목록 가져오기
	@ResponseBody
	@RequestMapping(value = "List.do",produces = "text/html; charset=UTF-8")
	public String list( @RequestParam Map map) {
		//서비스 호출
		List<Map> list = commentService.selectList(map);
		//pom.xml에서 json-simple 라이브러리 등록 후 사용!
		//JSONArray.toJSONString(list);//반드시 매개변수 list는 List<Map>이여야 한다!!! 
		//그럼 json 배열로 만들어 준다.
		//System.out.println(JSONArray.toJSONString(list));
		//날짜 값을 문자열로 변경해야한다 그렇지 않으면 JSON형식에 맞지 않는다.
		//2018-09-12형태로 변경
		for(Map comment:list) {
			comment.put("CPOSTDATE", comment.get("CPOSTDATE").toString().substring(0,10));
		}
		return JSONArray.toJSONString(list); 
	}
	
	
	
	//코멘트 수정 처리
	@ResponseBody
	@RequestMapping(value = "Edit.do",produces = "text/html; charset=UTF-8")
	public String update(@RequestParam Map map) {
		//서비스 호출
		commentService.update(map);
		//원본글: 댓글 번호 반환(확인 차원에서 하는 것 null이나 빈 문자열 반환해도 된다.)
		return String.format("%s : %s", map.get("no").toString(),map.get("cno").toString());
		
	}
	
	
	//코멘트 삭제 처리
	@ResponseBody
	@RequestMapping(value = "Delete.do",produces = "text/html; charset=UTF-8")
	public String delete(@RequestParam Map map) {
		//서비스 호출
		commentService.delete(map);
		//삭제 댓글 번호 반환 (확인용)
		return String.format("%s", map.get("cno").toString());
		
	}
	
	
	
}////////////CommentController
