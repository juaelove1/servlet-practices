package com.biacademy.emaillist.dao;

import java.util.List;

import com.bitacademy.emaillist.dao.EmaillistDao;
import com.bitacademy.emaillist.vo.EmaillistVo;

public class EmailListDaoTest {
	
	public static void main(String[] args) {
		
		//insert
		//testInsert();
		
		
		//findAll
		testFindAll();
		
	}
	
	
	public static void testInsert() {
		
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName("마");
		vo.setLastName("이콜");
		vo.setEmail("michol@gmail.com");
		
		new EmaillistDao().insert(vo);
		
		
	}
	
	
	
	public static void testFindAll() {
		
		List<EmaillistVo> list = new EmaillistDao().findAll();
		
		for(EmaillistVo vo : list) {
			
			System.out.println(vo);
		}
		
		
		
		
		
		
		
	}

}
