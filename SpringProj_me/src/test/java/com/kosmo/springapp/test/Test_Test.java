package com.kosmo.springapp.test;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//클래스 시작시 단 한번 시작되는 메서드
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//클래스 끝나기전 단 한번 시작되는 메서드
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
