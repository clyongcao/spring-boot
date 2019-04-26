package com.clyon.test;

import java.math.BigDecimal;
import org.junit.Test;

/*
* @Description
* @author Caoxuyang 
* @date 2019年1月11日 下午1:22:21 
*/

public class BigDecimalTest {

	public static void main(String[] args) {

		BigDecimal tenThousand = new BigDecimal("10000");

		BigDecimal guaranteeDepositOriginal = new BigDecimal("50000.00");
		BigDecimal technologyDepositOriginal = new BigDecimal("50000.00");
		BigDecimal commissionRateOriginal = new BigDecimal("5.0");

		BigDecimal guaranteeDeposit = guaranteeDepositOriginal.divide(tenThousand, 2, BigDecimal.ROUND_UP);
		BigDecimal technologyDeposit = technologyDepositOriginal.divide(tenThousand, 2, BigDecimal.ROUND_UP);
		BigDecimal commissionRate = commissionRateOriginal;

		System.out.println("guaranteeDeposit: " + guaranteeDeposit);
		System.out.println("technologyDeposit: " + technologyDeposit);
		System.out.println("commissionRate: " + commissionRate);

	}

	// 取余BD
	@Test
	public void testDivideAndRemainder() {

		BigDecimal amt = new BigDecimal(400);
		BigDecimal[] results = amt.divideAndRemainder(BigDecimal.valueOf(100));
		System.out.println(results[0]);
		System.out.println(results[1]);

	}

	@Test
	public void testCompare() {

		BigDecimal aimBigDecimal = new BigDecimal("100000.00");
		BigDecimal maxBigDecimal = new BigDecimal("100000");

		int compareMax = aimBigDecimal.compareTo(maxBigDecimal);
		// int compareResGt = aimBigDecimal.compareTo(maxBigDecimal);

		System.out.println(compareMax);

	}
	
	@Test
	public void testDivide() {

		BigDecimal aimBigDecimal = new BigDecimal("100000.00");
		BigDecimal divideBigDecimal = new BigDecimal("100000");

		BigDecimal resBigDecimal = aimBigDecimal.divide(divideBigDecimal, 2, BigDecimal.ROUND_UP);
		// int compareResGt = aimBigDecimal.compareTo(maxBigDecimal);

		System.out.println(resBigDecimal);

	}

}
