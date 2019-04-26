package com.clyon.test;

import java.util.UUID;

/*
* @Description
* @author Caoxuyang 
* @date 2018年11月2日 下午3:46:52 
*/
public class UUIDGeneratorTest {

	public static void main(String[] args) {
		
		UUID uu1 = UUID.randomUUID();
		String uustr1 = uu1.toString();
		
		UUID uu2 = UUID.randomUUID();
		String uustr2 = uu2.toString();
		
		System.out.println("uu1     : "+uu1);
		System.out.println("uustr1  : "+uustr1);
		
		System.out.println("uu2     : "+uu2);
		System.out.println("uustr2  : "+uustr2);
		
	}
	
}
