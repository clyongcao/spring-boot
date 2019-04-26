package com.clyon.test;

import java.util.ArrayList;
import java.util.List;

/*
* @Description
* @author Caoxuyang 
* @date 2018年10月28日 上午11:05:06 
*/
public class NullTest {
	
	public static void main(String[] args) {
		
		TestUseBean tub1 = new TestUseBean();
		TestUseBean tub2 = new TestUseBean();
		TestUseBean tub3 = new TestUseBean();
		TestUseBean tub4 = new TestUseBean();
		TestUseBean tub5 = new TestUseBean();
		
		tub1.setName(null);
		tub1.setAge(15);
		
		tub2.setName("joop");
		tub2.setAge(null);
		
		tub3.setName("yer");
		tub3.setAge(17);
		
		tub4.setName(null);
		tub4.setAge(null);
		
		List<TestUseBean> tubs = new ArrayList<>();
		
		tubs.add(tub1);
		tubs.add(tub1);
		tubs.add(tub2);
		tubs.add(tub3);
		tubs.add(tub4);
		tubs.add(tub5);
		tubs.add(null);
		
		//int len1 = tub.getName().length();
		int len2 = "".length();
		
		System.out.println("TestUseBean : "+tubs);
		//System.out.println(len1);
		System.out.println("length : "+len2);
		
		
		
	}
	

}

