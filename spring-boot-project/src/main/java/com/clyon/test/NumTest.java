package com.clyon.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

/*
* @Description
* @author Caoxuyang 
* @date 2018年11月8日 下午4:28:52 
*/
public class NumTest {

	public static void main(String[] args) {

		BigDecimal totalAmount = new BigDecimal("1654.15");
		BigDecimal compareTotalAmount = new BigDecimal("851.52");

		Double totalAmountD = Double.valueOf((totalAmount.toString()));
		Double compareTotalAmountD = Double.valueOf((compareTotalAmount.toString()));

		Double per = (totalAmountD - compareTotalAmountD) / compareTotalAmountD;

		System.out.println(per);

	}

	@Test
	public void test1() {

		Integer totalOrders = 151;
		Integer comparetotalOrders = 0;

		double dOCP = (((double) totalOrders - (double) comparetotalOrders) / (double) comparetotalOrders) * 100F;

		DecimalFormat df = new DecimalFormat("#.00");
		String dOCPStr = df.format(dOCP);

		if (comparetotalOrders.equals(0)) {
			dOCPStr = "—";
		}

		System.out.println("ordersComparePer : " + dOCPStr);

		/*
		 * Map<String,Object> ordersGraph = new LinkedHashMap<>();
		 * 
		 * HashMap<String, Integer> totalOrdersMap = new HashMap<String, Integer>();
		 * totalOrdersMap.put("totalOrders", totalOrders);
		 * 
		 * HashMap<String, String> ordersComparePerMap = new HashMap<String, String>();
		 * ordersComparePerMap.put("ordersComparePer", dOCPStr);
		 */

	}

	@Test
	public void test2() {

		BigDecimal totalAmount = new BigDecimal("2001.60");

		BigDecimal compareTotalAmount = new BigDecimal("1153.00");

		Double totalAmountD = Double.valueOf((totalAmount.toString()));
		Double compareTotalAmountD = Double.valueOf((compareTotalAmount.toString()));

		BigDecimal zeroBd = new BigDecimal("0.00");

		Double AmountComparePerD = ((totalAmountD - compareTotalAmountD) / compareTotalAmountD) * 100F;

		DecimalFormat df = new DecimalFormat("#.00");
		String aCPStr = df.format(AmountComparePerD);

		if (compareTotalAmount.compareTo(zeroBd) == 0) {
			aCPStr = "-";
		}

		System.out.println("1: "+" AmountComparePer : " + aCPStr);
		
		
		String amountStr = "26";
		
		BigDecimal amount =  new BigDecimal(amountStr + "0000.00");
		
		System.out.println("2: "+"amount : "+amount);

	}
	
	@Test
	public void doubleTest() {
		
		double differOrders = 0.00F;
		
		System.out.println("equlas? : "+ (differOrders == 0));
		
	}
	
	@Test
	public void regxTest() {
		
		double differOrders = 0.00F;
		
		System.out.println("equlas? : "+ (differOrders == 0));
		
	}
	
	@Test
	public void intergerTest() {
		
		Integer a = 0;
		int b = 0;
		
		System.out.println("res: " + (a <= b));
		
	}

}
