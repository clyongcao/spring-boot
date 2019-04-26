package com.clyon.test;

import org.junit.Test;

public class Syso {

	public static void main(String[] args) {
	
		System.out.println("HelloWorld!");
		
	}
	
	@Test
	public void test1 () {
		
		DateTest dt1 = new DateTest();
		
		DateTest dtEx1 = (DateTest) new DateTestEx();
		
		DateTestEx dtEx2 = new DateTestEx();
		
		System.out.println("dt1:"+dt1.a);
		System.out.println("dtEx1:"+dtEx1.a);
		System.out.println("dtEx2:"+dtEx2.a);
		
	}
	
}
