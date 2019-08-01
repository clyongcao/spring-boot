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

		String str = "gofods";
		String telephone = "^0{0,1}1(3|4|5|6|7|8|9)[0-9]{9}$";
		String yearStr = "2018";
		String monthStr = "201904";
		String regEx01 = "^(0|1){1}$";
		String regEx02 = "^[1-9][0-9]{0,4}$";
		String regEx03 = "(\\d{6})";
		String regEx04 = "[1-9][0-9]{4,9}";
		String strt = "18855662323";
		String words = "^(start|none|goods|goods_group|shop|url)$";
		String regMonth = "^[1-9]\\d{3}-\\d{1,2}$";
		String regYear = "^[1-9]\\d{3}$";

		Pattern p = Pattern.compile(regMonth);
		Matcher m = p.matcher(monthStr);
		boolean result = m.find();
		System.out.println("1: " + result);

		Pattern pt = Pattern.compile(regYear);
		Matcher mt = pt.matcher(yearStr);
		boolean resultt = mt.find();
		System.out.println("2: " + resultt);
		
		Pattern.compile(regMonth).matcher(monthStr).find();

	}

}
