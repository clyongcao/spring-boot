package com.clyon.test;

import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

/*
* @Description
* @author Caoxuyang 
* @date 2018年11月8日 上午10:49:42 
*/
public class StringTest {

	public static void main(String[] args) {

		// String ifStr = "TWMD" ;

		System.out.println("TWMD".indexOf("T"));
		System.out.println("TWMD".indexOf("t"));
		System.out.println("TWMD".indexOf("M"));
		System.out.println("TWMD".indexOf("m"));
		System.out.println("TWMD".indexOf("l"));

		System.out.println(
				"http://img01.sys.iflashbuy.com:8282/group2/M00/07/A5/wKgF-lvVpC-AUq7xAAB3yqOhYD4789.jpg".length());

		System.out.println("广东省广州市" + null);

		System.out.println(new HashMap());

	}

	@Test
	public void splitTest() {

		String str1 = "http://img01.sys.iflashbuy.com:8282/group2/M00/00/85/wKgFZFvRfNGAdl6AAACr5qucfME189.jpg;http://img01.sys.iflashbuy.com:8282/group2/M00/00/85/wKgFZFvRfNGAdl6AAACr5qucfME189.jpg;http://img01.sys.iflashbuy.com:8282/group2/M00/00/85/wKgFZFvRfNGAdl6AAACr5qucfME189.jpg;";
		String str2 = "http://img01.sys.iflashbuy.com:8282/group2/M00/00/85/wKgFZFvRcAyAYoeCAAdqVBqAw9g699.jpg;";

		String[] str1s = str1.split(";");
		String[] str2s = str2.split(";");

		for (String string : str1s) {
			System.out.println(string);
			// System.out.println(string.length());
		}
		System.out.println("***************************************************************************************");
		for (String string : str2s) {
			System.out.println(string);
		}

	}

	@Test
	public void stringBuilderTest() {

		StringBuilder sb = new StringBuilder();

		String str1 = "dasdwsfdfwe;this is a new branch;fewfcxfcsdfg";
		String str2 = "iyjtjfgjfjy;bnytuigreadf";

		sb.append(str1);
		sb.append(";");
		sb.append(str2);

		System.out.println(sb.toString());

	}
 
	@Test
	public void triangle() {

		for (int i = 0; i < 5; i++) {

			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

	}

	@Test
	public void equalsTest1() {

		String a = "品牌权书";
		String b = "品牌授权书";
		String c = "品牌注册证书";

		if (a.equals("品牌授权书") || a.equals("品牌注册证书")) {
			System.out.println(a + " a进");
		}
		if (b.equals("品牌授权书") || b.equals("品牌注册证书")) {
			System.out.println(b + " b进");
		}
		if (c.equals("品牌授权书") || c.equals("品牌注册证书")) {
			System.out.println(c + " c进");
		}

	}
	
	@Test
	public void equalsTest2() {
	StringBuilder sb = new StringBuilder();
	StringBuilder sb2 = new StringBuilder();
	sb.append("SELECT sponsor,bind_merchant_id bindMerchantId,bind_store_id bindStoreId, "
			+ " sponsor_merchant_id sponsorMerchantId,sponsor_store_id sponsorStoreId, "
			+ " status,cancelor,create_by createBy,update_by updateBy,create_time createTime, "
			+ " update_time updateTime,audit_remark auditRemark,cancel_remark cancelRemark "
			+ " FROM t_merchant_shop_bind WHERE is_delete=0 AND ");
	
	sb2.append("SELECT m.id,m.name,m.merchant_code merchantCode, "
			+ " m.contact_man contactMan,m.telephone,m.create_time createTime, "
			+ " m.create_type createType,m.whether_invoice whetherInvoice,"
			+ " p.audit_status status,s.name storeName "
			+ " FROM t_merchant m "
			+ " LEFT JOIN t_merchant_progress p ON p.merchant_id=m.id"
			+ " LEFT JOIN t_merchant_store s ON s.merchant_id=m.id "
			+ " WHERE m.is_delete = 0 ");
	
	System.out.println(sb2.toString());
	}
}
