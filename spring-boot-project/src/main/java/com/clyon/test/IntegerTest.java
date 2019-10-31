package com.clyon.test;

import org.junit.Test;

public class IntegerTest {

	public static void main(String[] args) {

		Integer a = null;
		Integer b = null;
		Integer c = 0;
		Integer d = 1;

		Integer ab = a + b;
		Integer ac = a + c;
		Integer ad = a + c;
		Integer cd = a + c;

		System.out.println(" ab：" + ab + " ac：" + ac + " ad：" + ad + " cd：" + cd);

	}
	
	@Test
	public void pe() {
		
		int a = 5;
		int b = 10;
		Integer c = 2;
		System.err.println(a += a);
		System.err.println(c += b);
		System.err.println(b += a);
	}
	
	@Test
	public void Ii() {
		
		int a = 130;
		Integer b = 130;
		Integer c = 130;
		Integer d = 130;
		
		System.err.println(a==b);
		System.err.println(c==d);
		System.err.println(b += a);
	}

}
