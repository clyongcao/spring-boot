package com.clyon.test;
/*
* @Description
* @author Caoxuyang 
* @date 2018年10月28日 上午11:05:36 
*/
public class TestUseBean {
	
	private String name;
	
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "TestUseBean [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
