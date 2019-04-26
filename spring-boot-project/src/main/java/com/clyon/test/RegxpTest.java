package com.clyon.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* @Description
* @author Caoxuyang 
* @date 2018年11月20日 下午5:58:34 
*/
public class RegxpTest {
	
	public static void main(String[] args) {
		
		String str = "1";
		String telephone = "^0{0,1}1(3|4|5|6|7|8|9)[0-9]{9}$";

		String regEx01 ="^(0|1){1}$"; 
		String regEx02 ="^[1-9][0-9]{0,4}$"; 
		String regEx03 ="(\\d{6})"; 
		String regEx04 = "[1-9][0-9]{4,9}";
		String strt = "18855662323";
				
		Pattern p=Pattern.compile(regEx02);
		Matcher m=p.matcher(str);
		boolean result=m.find();
		System.out.println(result);
		
		Pattern pt=Pattern.compile(telephone);
		Matcher mt=pt.matcher(strt);
		boolean resultt =mt.find();
		System.out.println("isTelephone : "+resultt);

	}
	
	

}
