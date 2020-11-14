package com.kosmo.springapp.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.jta.SpringJtaSynchronizationAdapter;


import com.kosmo.springapp.onememo.service.impl.OneMemoDAO;



/*
 org.junit등에 레드 라인 해결법
 프로젝트 우클릭 -> Build Path ->Configure Build Path ->
 Libraries탭 활성화 후 ->Add Libraries클릭 ->JUNI선택->버전선택


클래스 생성:new ->other에서 JUNIT으로 검색->Junit Test Case로 클래스 생성

namE항목 - 클래스명:테스트 대상 클래스명뒤에 Test를 붙인다(권장사항)
class under test항목 - 테스트 대상 클래스를  지정
아래 테스트 클래스 실행-Run As->JUnit Test
*/

public class CommonUtilitiesTest {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonUtilitiesTest.class);
	
	
	
	private CommonUtilities common;
	
	/*테스트 클래스가 인스턴스화되서 모든 테스트 메소드가    실행되기 전에 딱 한번만 호출되는 메소드*/
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.info("[@BeforeClass]");
	}
	/*모든 테스트 메소드가 끝나고(실행되고나서) 딱 한번만 호출되는 메소드*/
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		logger.info("[@AfterClass]");
	}
	/* 각 테스트 대상 메소드가 실행되기 전에 호출되는 메소드*/
	@Before
	public void setUp() throws Exception {		
		common = new CommonUtilities();
	}
	/* 각 테스트 대상 메소드가 실행된 후에 호출되는 메소드*/
	@After
	public void tearDown() throws Exception {
		logger.info("[@After]");
	}
	/*@Test 어노테이션:아래 메소드는 테스트 대상이되는    메소드임을 컴파일러에게 알려주는  어노테이션
              테스트 메소드명: test대상메소드명*/
	
	@Test
	@Ignore/*아래 메소드는 테스트 대상이지만 테스트 안하겠다라는 의미*/
	public void testCommonUtilities() {//기본 생성자 테스트
		/*단정문:예상치와 실제치가 같다고 단정을 짓는다
    	    같으면 성공 틀리면 실패
   		예]assertEquals:가장 많이 쓰고 데이타가 같은지 비교
		 */
		assertEquals(1, common.getValue());
		logger.info("기본 생성자 테스트-실제치(common.getValue()):"+common.getValue());
	}//////////
	@Test
	public void testCommonUtilitiesArgs() {//인자 생성자 테스트
		CommonUtilities common = new CommonUtilities(10);
		assertEquals(10, common.getValue());
		logger.info("인자생성자 테스트-실제치(common.getValue()):"+common.getValue());
	}//////
	@Test
	public void testAdd() {
		assertEquals(10, common.add(9));
		logger.info("add()메소드 테스트");
	}
	/* expected에 지정한 예외 클래스 발생시 성공,아니면 실패*/
	@Test(expected = NumberFormatException.class)
	public void testError() {
		common.error();
	}
	/* 결과가 true면 성공,false면 실패*/
	@Test
	public void testIsBool() {
		assertTrue(common.isBool(0));
	}///////
	
	/* 테스트 대상 클래스에 없는 메소드 객체 비교 단정문:assertSame() */
	@Test
	public void testSame() {
		CommonUtilities common2 = new CommonUtilities();
		common = common2;
		assertSame(common, common2);
	}
	//배열에 저장된 데이타 비교, 같으면 성공,다르면 실패
	@Test
	public void testArray() {
		int [] arr1= {1,2,3,4,5};
		int [] arr2= {1,2,3,4,5};
		assertArrayEquals(arr1, arr2);
	}
	
	@Test
	public void testIsNumber() {
		assertFalse(CommonUtilities.isNumber("백"));		
	}

	@Test
	public void testGetDifferenceDates() {
		try {
			assertEquals(1, CommonUtilities.getDifferenceDates("2020-11-11","2020-11-12", "yyyy-MM-dd", 'D'));
		}
		catch(Exception e) {}
	}

	@Test
	public void testGetFirstCharacter() {
		assertEquals('ㄱ',CommonUtilities.getFirstCharacter("고길동"));
	}
	
	

}
