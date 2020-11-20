package com.kosmo.springapp.basic.fileupdown;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DownloadController {
	//방법 1) api 미사용
	/*
	@RequestMapping("/FileUpDown/Download.do")
	public void download(HttpServletRequest req, HttpServletResponse resp, @RequestParam String filename) {
		FileUpDownUtils.download(req, resp, filename, "/upload");
	}///////////
	*/
	
	//방법2) use Spring api -반환 타입은 반드시 String (문자열은 빈 설정파일에 등록할 뷰 객체의 id다)
	@RequestMapping("/FileUpDown/Download.do")
	public String download(Model model, HttpServletRequest req) {

		String path = req.getServletContext().getRealPath("/upload");
		String filename = req.getParameter("filename");
		File file = new File(path+File.separator+filename);
		
		model.addAttribute("file",file);//파일 객체를 model에 저장하면  자동 다운로드
		
		return "downloadView";
	}
	
	
}
