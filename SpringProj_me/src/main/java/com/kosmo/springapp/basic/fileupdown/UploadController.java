package com.kosmo.springapp.basic.fileupdown;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	//file 업로드시 MultipartFile API 를 사용
	//method:
	//getOriginalFilename()-사용자가 올린 파일명
	//getSize() -파일크기(byte)
	//getContent()-파일 컨텐트 타입
	//getName() - input type="file" 의 name속성에 지정한 값//파일명이 아니다!!!!!
	//transferTo(File f) -호출로 파일이 업로드 된다.
	
	/*
	 * 방법  1) @RequestParam MultipartFile 매개변수로 사용
	 * @RequestParam Map map으로 파라미터 받기
	 * 
	 * servlet 설정 파일에 
	 * org.springframework.web.multipart.commons.CommonsMultipartResolver 설정하기
	 * 아이디는 반드시 multipartResolver로 하기!!!
	 * 
	 * 
	 * enctype="multipart/form-data"일 경우,
	 * Map은 input type="file"을 받지 못 한다.
	 * 또한 checkbox(여러개 선택해도) 는 하나만 받는다.
	 * 
	 */
	/*
	@RequestMapping("/FileUpDown/Upload.do")
	public String upload(@RequestParam Map map, //아래 제외 나머지 받기
			@RequestParam MultipartFile upload,//type='file'을 받아온다., 변수명은 name명으로
			HttpServletRequest req //서버의 물리적 경로 얻기용
			) throws IllegalStateException, IOException {
		//1) 서버의 물리적 경로
		String physicalPath = req.getServletContext().getRealPath("/upload");
		//2) File 객체 생성
		File file = new File(physicalPath+File.separator+upload.getOriginalFilename());
		//3) 파일 업로드 처리
		upload.transferTo(file);
		//4) 업로드 확인을 위한 정보뿌려주기 처리
		req.setAttribute("writer", map.get("writer"));
		req.setAttribute("title", map.get("title"));
		//4-1) 업로드와 관련된 파일정보
		req.setAttribute("original", upload.getOriginalFilename());
		//req.setAttribute("real", );
		req.setAttribute("type", upload.getContentType());
		req.setAttribute("size", (int)Math.ceil(upload.getSize()/1024.0));
		
		return "fileupdown13/UploadComplete";
	}//////////////////upload
	*/
	
	
	/*
	 * 방법2)
	 * MultipartHttpServletRequest 사용
	 * 
	 * getFile 메서드가 있어서 input type='file'을 얻기가 가능.
	 */
	/*
	@RequestMapping("/FileUpDown/Upload.do")
	public String upload(MultipartHttpServletRequest mhsr) throws IllegalStateException, IOException {
		//1) 서버의 물리적 경로
		String physicalPath = mhsr.getServletContext().getRealPath("/upload");
		//1-1) getFile로 MultipartFile 객체 얻기
		MultipartFile upload = mhsr.getFile("upload");
		//2) File 객체 생성
		//2-1) 파일 중복시 이름 변경
		String renameFilename = FileUpDownUtils.getNewFileName(physicalPath,upload.getOriginalFilename());
		File file = new File(physicalPath+File.separator+renameFilename);
		//3) 파일 업로드 처리
		upload.transferTo(file);
		//4) 업로드 확인을 위한 정보뿌려주기 처리
		mhsr.setAttribute("writer", mhsr.getParameter("writer"));
		mhsr.setAttribute("title", mhsr.getParameter("title"));
		//4-1) 업로드와 관련된 파일정보
		mhsr.setAttribute("original", upload.getOriginalFilename());
		mhsr.setAttribute("real", renameFilename);
		mhsr.setAttribute("type", upload.getContentType());
		mhsr.setAttribute("size", (int)Math.ceil(upload.getSize()/1024.0));
		
		return "fileupdown13/UploadComplete";
	}////////upload
	*/
	
	/*
	 * 방법3) 커멘드 객체 사용 DTO
	 */
	@RequestMapping("/FileUpDown/Upload.do")
	public String upload(UploadCommand cmd, HttpServletRequest req) throws IllegalStateException, IOException {
		//1) 서버의 물리적 경로
		String physicalPath = req.getServletContext().getRealPath("/upload");
		//1-1) file객체생성을 위한 
		MultipartFile upload = cmd.getUpload();
		//2) File 객체 생성
		//2-1) 파일 중복시 이름 변경
		String renameFilename = FileUpDownUtils.getNewFileName(physicalPath,upload.getOriginalFilename());
		File file = new File(physicalPath+File.separator+renameFilename);
		//3) 파일 업로드 처리
		upload.transferTo(file);
		//4) 업로드 확인을 위한 정보뿌려주기 처리
		req.setAttribute("writer", cmd.getWriter());
		req.setAttribute("title", cmd.getTitle());
		//4-1) 업로드와 관련된 파일정보
		req.setAttribute("original", upload.getOriginalFilename());
		req.setAttribute("real", renameFilename);
		req.setAttribute("type", upload.getContentType());
		req.setAttribute("size", (int)Math.ceil(upload.getSize()/1024.0));
		
		return "fileupdown13/UploadComplete";
	}///////////
	
	
	
	//목록이동용 컨트롤러 메서드
	@RequestMapping("/FileUpDown/List.do")
	public String list(HttpServletRequest req) {
		//1) 서버의 물리적 경로
		String physicalPath = req.getServletContext().getRealPath("/upload");
		//2) 경로에 대한 File 객체 생성
		File file = new File(physicalPath);
		File[] files = file.listFiles();
		//3) 영역에 저장 //방법1
		req.setAttribute("files", files);
		//방법2
		List<Map> list = new Vector<Map>();
		for(File file1 : files) {
			Map map = new HashMap();
			map.put("name", file1.getName());
			map.put("size", (int)Math.ceil(file1.length()/1024.0));
			list.add(map);
		}
		req.setAttribute("list", list);
		return "fileupdown13/List";
	}//////////list
	
	
	
}
