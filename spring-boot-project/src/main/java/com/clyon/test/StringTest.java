package com.clyon.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

/*
* @Description
* @author Caoxuyang 
* @date 2018年11月8日 上午10:49:42 
*/
public class StringTest {

	public static void main(String[] args) {
		
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
		System.out.println("*************************************************************************************");
		for (String string : str2s) {
			System.out.println(string);
		}

		String content = "dsd";

		String[] contentSplit = content.split(",");

		for (String string : contentSplit) {
			System.out.println("have','：" + string);
		}

		if (contentSplit.length > 3) {
			System.out.println("超啦！");
		} else {
			System.out.println("没超！");
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
				+ " p.audit_status status,s.name storeName " + " FROM t_merchant m "
				+ " LEFT JOIN t_merchant_progress p ON p.merchant_id=m.id"
				+ " LEFT JOIN t_merchant_store s ON s.merchant_id=m.id " + " WHERE m.is_delete = 0 ");

		System.out.println(sb2.toString());
	}

	@Test
	public void indexOfTest() {

		String content = "你是划老公！";
		String sensitiveWord = "划老公";

		Integer res = content.indexOf(sensitiveWord);

		System.out.println(res);

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
	public void substringTest() {

		String content = "开头是啥，我测测";

		System.out.println(content.substring(0, 1));

		String title = "【仅剩?1个名额】快和我一起在码上花?2元拼?3";

		System.out.println(title.substring(title.indexOf("】")+1));

	}

	@Test
	public void nullToStrTest() {

		System.out.println(nullToStr("开头是啥，我测测"));
		System.out.println(nullToStr(""));
		System.out.println(nullToStr(new BigDecimal("2150.00")));
		System.out.println(nullToStr(null));
		String a = "f";
		String b = "f";
		System.out.println(a == b);
		System.out.println(a.equals(b));

	}

	public String nullToStr(Object value) {

		if (value == null || value == "") {
			return "-";
		} else {
			return String.valueOf(value);
		}

	}

	@Test
	public void testSplit() throws Exception {

		List<Integer> idList = new ArrayList<>();
		/*
		 * String[] idStrs = new String[] { "15", "20", "1070" }; for (String idstr :
		 * idStrs) { idList.add(Integer.valueOf(idstr)); }
		 * 
		 * if (idList.size() == 0) { throw new
		 * ServiceException(GlobalStatusCode.CODE_770012.value(),
		 * GlobalStatusCode.CODE_770012.remark()); }
		 * 
		 * for(Integer id : idList) { System.out.println(); }
		 */
	}

	@Test
	public void testEquals() throws Exception {

		String dog1 = new String("coco");
		String dog2 = new String("coco");

		Syso s1 = new Syso("coco");
		Syso s2 = new Syso("coco");

		System.out.println(dog1.equals(dog2));
		System.out.println(s1.equals(s2));

	}

	@Test
	public void testSM() throws Exception {
		int status = 5;
		System.out.println(status == 5 ? "" : "hello");
	}

	@Test
	public void testRepalce() throws Exception {
		String title1 = "dsdb?1sdkfhjksd";
		title1 = title1.replace("替换", "?1");
		System.out.println(title1);

		String title2 = "dsdb?1sdkfhjksd";
		title2 = title2.replace("?1", "替换");
		System.out.println(title2);
	}

	@Test
	public void testIndexof() throws Exception {
		String url = "http://tmsh-m.z-code.cn:8383/fightGroup/detail?";
		int i1 = url.indexOf("fig");
		int i2 = url.indexOf(10);
		String title = url.substring(url.indexOf("http"), url.indexOf("/f"));
		System.out.println(i1 + " " + i2);
		System.out.println(title);
	}

	@Test
	public void testFormat() throws Exception {
		String s1 = "%s只要%s元，我正在码上花进行砍价";
		String s2 = "只要%s元砍%s，我正在码上花进行砍价,快来参加%s活动";
		
		String s1s = s1.format(s1, "欧莱雅", 99.99);
		String s2s = s2.format(s2, 99.99, "欧莱雅", "拼团");
		
		System.out.println(String.format("%%%s%%", "替换"));
		
		int num = 123456;
		String numStr = "0000000" + num;
		numStr = numStr.substring(numStr.length() - 6);
		
		System.out.println(numStr);
		//System.out.println(s2s);
		
		Integer getStatus = 0;
		Integer getAuditState = 3;
		
		if (getStatus.equals(0) && getAuditState.equals(3)) {
           System.err.println("上");
        } else {
        	System.err.println("下");
        }
	}

}
