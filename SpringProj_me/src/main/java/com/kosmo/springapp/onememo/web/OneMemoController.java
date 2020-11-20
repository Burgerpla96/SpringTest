package com.kosmo.springapp.onememo.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;
import com.kosmo.springapp.onememo.service.PagingUtil;

/*
 * ※스프링 씨큐리티 사용시에는 
 *  기존방식의 로그인처리 및 로그인 여부 판단 그리고 로그아웃등
 *  모두 제거한다(.jsp 에서 혹은 .java에서)
 *  그리고 나서 스프링 씨큐리티에서 제공하는 API로 처리한다
 *  단,로그인처리 및 로그아웃은 스프링 씨큐리티에서 제공함으로
 *  로그인 판단 여부만 처리하면 된다.
 */

@SessionAttributes("id")//스프링 씨큐리티를 사용하지 않을때
@RequestMapping("/OneMemo/BBS/")
@Controller
public class OneMemoController {
	
	//서비스 주입]
	@Resource(name="memoService")
	private OneMemoService memoService;
	
	
	//id 없을때 예외 처리 @ModelAttribute에서의 에러
	@ExceptionHandler({HttpSessionRequiredException.class})
	public String notLogin(Model model) {
		model.addAttribute("NotMember","로그인후 이용바람...");
		//로그인이 안된 경우 로그인 페이지로 이동
		return "onememo10/member/Login";
	}
	
	
	//리소스 파일 (onememo.properties)에서 읽어오기
	//@Value 롬복꺼 아니다!!
	@Value("${PAGE_SIZE}")
	private int pageSize;
	@Value("${BLOCK_PAGE}")
	private int blockPage;
	//목록 처리]
	@RequestMapping("List.do")
	public String list(
			@ModelAttribute("id") String id,
			@RequestParam Map map,
			Model model,
			@RequestParam(required = false, defaultValue = "1") int nowPage,
			HttpServletRequest req){//context root경로얻기용
		//session에서 id가져오기, isLogin 불필요
		//서비스 호출]
		
		//페이징을 위한 로직 시작)
		//전체 레코드 수
		int totalRecordCount = memoService.getTotalRecord(map);
		//페이지 사이즈, blockPage는 위에서 주입함...
		
		//전체 페이지 수
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize); 
		//현재 페이지 번호 -- @RequestParam(required = false, defaultValue = "1") int nowPage 처리...
		
		//시작 및 끝 ROWNUM 구하기
		int start = (nowPage-1) * pageSize+1;
		int end = nowPage * pageSize;
		//페이징을 위한 로직 끝)
		
		
		map.put("start", start);		
		map.put("end", end);		
		
		
		List<OneMemoDTO> list= memoService.selectList(map);
		//데이타 저장]
		String path = req.getContextPath();
		if(map.get("searchWord")!=null) {
			path += "/OneMemo/BBS/List.do?searchWord=" 
					+map.get("searchWord")+"&searchColumn="+map.get("searchColumn")+"&";
			//마지막 & 는 PagingUtil.java에서 만든 로직 때문...
		}
		else {
			path += "/OneMemo/BBS/List.do?";
		}
		
		//추가 --페이징 뿌려주기
		String pagingString = 
				PagingUtil.pagingBootStrapStyle(totalRecordCount, pageSize, blockPage, nowPage, path);
		model.addAttribute("list", list);
		model.addAttribute("pagingString", pagingString);
		model.addAttribute("totalRecordCount", totalRecordCount);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("pageSize", pageSize);
		//뷰정보 반환]
		return "onememo10/bbs/List";
	}
	
	
	//입력폼으로 이동
	@RequestMapping(value="Write.do", method = RequestMethod.GET)
	public String write(
			@ModelAttribute("id") String id
			//로그인 하지 않은 채로 Write.do URL로 접근시 에서의 판단 위함
			) {
		
		//뷰정보 반환]
		return "onememo10/bbs/Write";
	}////////////write
	
	
	
	//입력처리
	@RequestMapping(value = "Write.do", method = RequestMethod.POST)
	public String writeOk(
			@ModelAttribute("id") String id,//시큐리티 미사용시
			@RequestParam Map map
			) {
		//누가 작성했는지 id도 전달하기 위한 작업
		map.put("id", id);
		//요청따라서 DB에 넣기
		memoService.insert(map);
		//뷰정보 반환]
		return "forward:/OneMemo/BBS/List.do";
	}///////////writeOk
	
	
	//상세보기
	@RequestMapping("View.do")
	public String view(@RequestParam Map map, Model model) {
		//서비스 호출
		OneMemoDTO record = memoService.selectOne(map);
		//줄바꿈 처리
		record.setContent(record.getContent().replace("\r\n", "<br/>"));
		//데이터 저장
		model.addAttribute("record",record);
		//뷰정보 반환
		return "onememo10/bbs/View";
	}//////////////////view
	
	
	
	//수정버튼 눌렀을시 수정 jsp로 이동   및  수정처리
	@RequestMapping("Edit.do")
	public String edit(HttpServletRequest req, @RequestParam Map map) {
		if(req.getMethod().equals("GET")) {//수정 폼으로 이동
			//서비스 호출
			OneMemoDTO record = memoService.selectOne(map);
			//데이터 저장
			req.setAttribute("record",record);
			//뷰정보 반환
			return "onememo10/bbs/Edit";
		}
		//수정작업 후의 이동
		//서비스 호출 --update
		memoService.update(map);
		//뷰정보 반환 -- 수정후 수정된 것 다시 보기
		return "forward:/OneMemo/BBS/View.do";
	}//////////////edit
	
	
	
	//삭제 처리
	@RequestMapping("Delete.do")
	public String delete(@RequestParam Map map) {
		//서비스 호출
		memoService.delete(map);
		//뷰정보 반환
		return "forward:/OneMemo/BBS/List.do";
	}/////////delete
	
	
	
	
	
	
	
	
	
	
	
	
}
