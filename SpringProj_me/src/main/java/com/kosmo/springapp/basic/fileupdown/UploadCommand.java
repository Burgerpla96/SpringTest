package com.kosmo.springapp.basic.fileupdown;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadCommand {
	private String writer;
	private String title;
	private MultipartFile upload;//input type='file'은 MultipartFile로 받기
	
	
}
