package com.clyon.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
* @Description
* @author Caoxuyang 
* @date 2018年12月18日 下午3:16:06 
*/
public class SetTest {

	public static void main(String[] args) {

		HashMap<String, Set<Integer>> mbsMap = new HashMap<>();

		Set<Integer> mbsSQSSet = new HashSet<>();
		mbsSQSSet.add(2);
		mbsSQSSet.add(3);
		mbsSQSSet.add(4);
		Set<Integer> mbsZCZSet = new HashSet<>();
		mbsZCZSet.add(3);
		mbsZCZSet.add(4);
		mbsZCZSet.add(5);
		Set<Integer> mbSZSet = new HashSet<>();

		mbsMap.put("品牌授权书", mbsSQSSet);
		mbsMap.put("品牌注册证书", mbsZCZSet);

		for (Integer mbsSQSId : mbsSQSSet) {
			for (Integer mbsZCZId : mbsZCZSet) {
				if(mbsSQSId == mbsZCZId) {
					
				}
			}
		}

	}

}
