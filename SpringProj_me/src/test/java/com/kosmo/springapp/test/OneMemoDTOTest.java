package com.kosmo.springapp.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.kosmo.springapp.onememo.service.OneMemoDTO;

public class OneMemoDTOTest {
	
	@Test
	public void test() {
		OneMemoDTO dto = new OneMemoDTO();		
		dto.setNo("10");
		assertEquals("10", dto.getNo());
		System.out.println("dto:"+dto);
	}

}
