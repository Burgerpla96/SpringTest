package com.kosmo.springapp.basic.fileupdown;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

//ViewResolver 로 만들게 상속 받기
public class DownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		/*
		 * 다운로드 컨트롤러에서 모델계얼(Map,Model,ModelMap) 에 저장한 파일객체가 
		 * 첫 번째 Map 에 저장된다.
		 */
		//다운로드 로직 구현
		FileUpDownUtils.download(req, resp, ((File)map.get("file")).getName(), "/upload");
		
	}////////

}
