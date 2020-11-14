package com.kosmo.springapp.onememo.service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemoCommentDTO {
	
	private String cno;	
	private String lineComment;
	private java.sql.Date cpostDate;
	private String no;
	private String id;
	private String name;
	
}
