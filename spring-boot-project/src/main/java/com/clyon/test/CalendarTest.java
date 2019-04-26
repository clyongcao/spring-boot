package com.clyon.test;

import java.util.Calendar;
import java.util.Date;

/*
* @Description
* @author Caoxuyang 
* @date 2019年1月11日 下午1:33:06 
*/
public class CalendarTest {
	
	public static void main(String[] args) {
		
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.YEAR, 1);
		Date afterOneYear = cal.getTime();
		
		System.out.println("now: "+now);
		System.out.println("afterOneYear: "+afterOneYear);
		
	}
}
