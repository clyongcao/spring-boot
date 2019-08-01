package com.clyon.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/*
* @Description
* @author Caoxuyang 
* @date 2018年10月27日 下午2:46:29 
*/
public class ListTest {

	public static void main(String[] args) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 2600; i++) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append("a");
		}

		for (int i = 0; i < 2; i++) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append("b");
		}

		String[] userIds = sb.toString().split(",");
		int len = userIds.length;

		/*
		 * System.out.println("自测次: " + 0 + "->" + len); String[] testa =
		 * Arrays.copyOfRange(userIds, 2000, 2657); System.out.println("自测次length: " +
		 * testa.length);
		 */

		/*
		 * List<String> lists = new ArrayList<>(); for(int i=0 ; i<list.size()/1000+1 ;
		 * i++) { lists.add("l"); }
		 * 
		 * System.out.println(lists.size());
		 */

		System.out.println("userIdsLength: " + len + "    " + "% : " + 1000 % 1000 + "  " + "/ : " + 151 / 1000);
		System.out.println();

		for (int i = 0; i < len / 1000 + 1; i++) {
			String[] alias = null;
			if (i == 0 && len <= 1000) {
				System.out.print("(A)第" + (i) + "次: " + "0000" + "->" + len);
				alias = Arrays.copyOfRange(userIds, 0, len);
			} else if (i == 0 && len > 1000) {
				System.out.print("(B)第" + i + "次: " + "0000" + "->" + 1000);
				alias = Arrays.copyOfRange(userIds, 0, 1000);
			} else if (i < len / 1000) {
				System.out.print("(C)第" + i + "次: " + i * 1000 + "->" + (i + 1) * 1000);
				alias = Arrays.copyOfRange(userIds, i * 1000, (i + 1) * 1000);
			} else {
				System.out.print("(D)第" + i + "次: " + ((i * 1000) + "->" + (i * 1000 + len % 1000 - 1)));
				alias = Arrays.copyOfRange(userIds, i * 1000, i * 1000 + len % 1000 - 1);
			}
			System.out.println("    aliasLength" + (i) + ": " + alias.length);
		}

		System.out.println("===========================================");

		for (int i = 0; i < len / 999 + 1; i++) {
			String[] alias = null;
			if (i == 0 && len <= 999) {
				System.out.print("(A)第" + (i) + "次: " + "0000" + "->" + len);
				alias = Arrays.copyOfRange(userIds, 0, len);
			} else if (i == 0 && len > 999) {
				System.out.print("(B)第" + i + "次: " + "0000" + "->" + 999);
				alias = Arrays.copyOfRange(userIds, 0, 999);
			} else if (i < len / 999) {
				System.out.print("(C)第" + i + "次: " + i * 999 + "->" + (i + 1) * 999);
				alias = Arrays.copyOfRange(userIds, i * 999, (i + 1) * 999);
			} else {
				System.out.print("(D)第" + i + "次: " + ((i * 999) + "->" + (i * 999 + len % 999 - 1)));
				alias = Arrays.copyOfRange(userIds, i * 999, (i * 999 + len % 999 - 1));
			}
			System.out.println("    aliasLength" + (i) + ": " + alias.length);
		}

	}

	@Test
	public void test1() {

	}

}
