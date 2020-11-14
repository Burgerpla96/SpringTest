package com.kosmo.springapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.impl.OneMemoDAO;

//※스프링 프레임워크에맞게 JUnit확장 라이브리 사용 JUnit을 확장한 스프링의 테스트 라이브러리
/*
1. pom.xml에 라이브러리등록
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-test</artifactId>
	<version>${org.springframework-version}</version>
</dependency>
2. 어노테이션 등록
@RunWith(SpringJUnit4ClassRunner.class)
:스프링의 컨텍스트를 테스트에 사용할 수 있도록 하기위한 설정

@ContextConfiguration(locations = "{classpath:xml파일위치,file:full path}")
@RunWith 어노테이션에 의해 생성된 컨텍스트가 사용하는 빈 설정 파일의 위치를 지정.
*/


/*
 *1/ Junit을 확장한 ... 등록
 * 단 스코프는 제거
 * 
 *2/  어노테이션 등록
 *	@RunWith(SpringJUnit4ClassRunner.class)
 *
 *	@ContextConfiguration(locations={classpath:xml파일위치, file...})주기
 *	
 *
 *
 *
 * 
 * 
※단위 테스트시 JNDI 사용하면 에러
   테스트시에는 로컬 데이터 소스를 사용해야한다.
  DataSource정의를 별도의 XML 파일로 가져 와서 테스트 중에 JNDI 대신 사용해야한다
    그래서 테스트시에는 root-context.xml파일을 src/test/resources에 있는 파일 사용
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class OneMemoDAOTest {
	
	@Autowired
	//@Resource(name = "oneMemoDAO")
	private OneMemoDAO dao;
	@Test
	public void testIsLogin() {
		Map map = new HashMap();
		map.put("id","KIM");
		map.put("pwd","1234");
		assertTrue(dao.isLogin(map));
	}
	
	@Test
	public void testSelectList() {
		List<OneMemoDTO> list =  dao.selectList(null);
		assertEquals(0, list.size());
	}
	
	

}
