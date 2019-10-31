package com.clyon.test;

import org.junit.Test;

public class MethodTest {

	@Test
	public void dog() {

		String name = "CoCo";

		nameAdd(name);

		System.out.println(name);
	}

	public void nameAdd(String name) {

		name = "We called this dog " + name;

		System.out.println(name);
	}

}
