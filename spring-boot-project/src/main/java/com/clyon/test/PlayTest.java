package com.clyon.test;

import java.util.Scanner;

/*
* @Description
* @author Caoxuyang 
* @date 2018年12月13日 下午7:09:54 
*/
public class PlayTest {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			String str = sc.next();
			str = str.replace("吗", "");
			str = str.replace("？", "！");
			str = str.replace("?", "!");
			
			System.out.println(str);
		}
		
	}
		
	}
	
