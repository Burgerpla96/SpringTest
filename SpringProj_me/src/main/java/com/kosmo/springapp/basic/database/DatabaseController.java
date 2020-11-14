package com.kosmo.springapp.basic.database;


import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * DataSource를 얻기위한 빈 등록은 root-context.xml에 등록
 * 그래야 각각의 디스패처 서블릿에서 해당 빈을 공통으로 사용가능
 */

@Controller
public class DatabaseController {
	
	//자동 주입
	@Resource(name = "dataSourceByJDBC")
	private DataSource jdbc;
	
	//주입
	@Resource(name = "dataSourceByJNDI")
	private DataSource jndi;
	
	
	
	//커넥션 생성으로 하는 법
	@RequestMapping("/Database/JDBConnection.do")
	public String jdbc(@RequestParam String method, Model model) throws SQLException {
		//conn연결
		Connection conn = jdbc.getConnection();
		//데이터 저장
		model.addAttribute("message",conn==null?"[데이터베이서 연결 실패]":"[데이터베이서 연결 성공: "+method+"]");
		//해제
		if(conn != null) conn.close();
		//뷰정보 반환
		return "database07/Database";
	}
	
	//커넥션 풀 사용하는 법
	@RequestMapping("/Database/JNDIConnection.do")
	public String jndi(@RequestParam String method, Model model) throws SQLException {
		//conn연결
		Connection conn = jndi.getConnection();
		//데이터 저장
		model.addAttribute("message",conn==null?"[데이터베이서 연결 실패]":"[데이터베이서 연결 성공: "+method+"]");
		//해제가 아닌 반납
		if(conn != null) conn.close();
		//뷰정보 반환
		return "database07/Database";
	}
	
	
}
