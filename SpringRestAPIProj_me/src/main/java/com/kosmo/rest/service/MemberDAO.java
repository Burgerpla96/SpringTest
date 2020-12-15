package com.kosmo.rest.service;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	//SqlSessionTemplate 객체 주입
	@Resource(name="template")
	private SqlSessionTemplate template;

	public MemberDTO isLogin(MemberDTO member) {
		return template.selectOne("memberIsLogin",member);
	}
	
	
	
}
