package com.clyon.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class DateTest {

	Date now = new Date();

	String a = "10";

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Long nowDateTimeMillis = System.currentTimeMillis();
		Long yesterdayDateTimeMillis = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
		Long selectDateTimeMillis = sdf.parse("2017-10-14").getTime(); // '2018-03-19' AND '2018-03-22'

		Date nowDate = new Date(nowDateTimeMillis);
		Date yesterdayDate = new Date(yesterdayDateTimeMillis);
		Date selectDate = new Date(selectDateTimeMillis);

		String nowDateStr = sdf.format(nowDate);
		String yesterdayDateStr = sdf.format(yesterdayDate);
		String selectDateStr = sdf.format(selectDate);

		int a = 0;
		a = 7 / 2;
		long al = a;

		System.out.println("oneDayms: " + 24 * 60 * 60 * 1000L + "  " + al);
		System.out.println(
				"timeType  :  CurrentTimeMillis  " + "                 Date    " + "               StringTime  ");
		System.out.println("***********************************************************************************");

		System.out.print("now       :  ");
		System.out.print("  " + nowDateTimeMillis + "        ");
		System.out.print(nowDate + "  ");
		System.out.println("      " + nowDateStr + "  ");

		System.out.print("yesterday :  ");
		System.out.print("  " + yesterdayDateTimeMillis + "        ");
		System.out.print(yesterdayDate + "  ");
		System.out.println("      " + yesterdayDateStr + "  ");

		System.out.print("select    :  ");
		System.out.print("  " + selectDateTimeMillis + "        ");
		System.out.print(selectDate + "  ");
		System.out.println("      " + selectDateStr + "  ");

	}

	@Test
	public void HHmmss() {

		SimpleDateFormat ordersdf = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");

		Date todayDate = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 8);
		String todayDateStr = ordersdf.format(todayDate);

		String shour = todayDateStr.substring(0, 2);
		Integer ishour = Integer.valueOf(shour);

		System.out.println(todayDate);
		System.out.println(todayDateStr);
		System.out.println(shour);
		System.out.println(ishour);

	}

	@Test
	public void subDateStr() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Long selectDateTimeMillis = sdf.parse("2018-11-28").getTime();

		String oSTYMStr = sdf.format(new Date(selectDateTimeMillis));

		Integer subOSTYMInt = Integer.valueOf(oSTYMStr.substring(5, 7));

		System.out.println("subOSTYMInt : " + subOSTYMInt);

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMM");
		System.out.println("yyyyMM : " + sdf2.format(now));

	}

	@Test
	public void DateLoop() throws ParseException {

		SimpleDateFormat yMDsdf = new SimpleDateFormat("yyyy-MM-dd");

		Map<String, Integer> ordersGraph = new LinkedHashMap<>();

		String oSTYMStr = "2018-02-05";
		String oETYMStr = "2018-02-06";

		long ordersStartTime = yMDsdf.parse(oSTYMStr).getTime(); // "2018-02-05"
		long ordersEndTime = yMDsdf.parse(oETYMStr).getTime(); // "2018-08-25"

		long orderdeltaTime = ordersEndTime - ordersStartTime;
		long threeMonsTime = 7776000000L; // 1000*60*60*24*3*30

		long day = orderdeltaTime / 86400000; // 1000*60*60*24
		System.out.println("day : " + day);

		Integer oSTYMInt = Integer.valueOf(oSTYMStr.substring(5, 7));
		Integer oETYMInt = Integer.valueOf(oETYMStr.substring(5, 7));

		ordersGraph.put(oSTYMStr, 0);
		long oSTYMLongP = ordersStartTime;
		String oSTYMStrP = yMDsdf.format(oSTYMLongP + 86400000);
		for (int i = 0; i < day; i++) {

			oSTYMLongP = oSTYMLongP + 86400000;
			oSTYMStrP = yMDsdf.format(oSTYMLongP);
			ordersGraph.put(oSTYMStrP, 0);

		}

		System.out.println(ordersGraph);

	}

	public void sEWeek(Date date) {

		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		int dayOfWeek = ca.get(Calendar.DAY_OF_WEEK);
		// 周一是开始
		if (dayOfWeek == 1) {
			dayOfWeek = 7;
		} else {
			dayOfWeek--;
		}
		Calendar cal = (Calendar) ca.clone();
		cal.add(Calendar.DATE, 1 - dayOfWeek);
		Date date1 = cal.getTime();
		cal = (Calendar) ca.clone();
		cal.add(Calendar.DATE, 7 - dayOfWeek);
		Date date2 = cal.getTime();
		String str1 = f.format(date1);
		String str2 = f.format(date2);

		System.out.println("开始：" + str1 + "  " + "结束" + str2);
	}

	public void sEMonth(Date date) {

		// 获取本月一号和后一天的日期：
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		int maximum = ca.getActualMaximum(Calendar.DAY_OF_MONTH);
		int minmum = ca.getActualMinimum(Calendar.DAY_OF_MONTH);
		int day = ca.get(Calendar.DAY_OF_MONTH);
		Calendar cal = (Calendar) ca.clone();
		cal.add(Calendar.DATE, maximum - day);
		Date dateD = cal.getTime();
		String strD = f.format(dateD);
		cal = (Calendar) ca.clone();
		cal.add(Calendar.DATE, minmum - day);
		Date dateX = cal.getTime();
		String strX = f.format(dateX);

		System.out.println("开始：" + strX + "  结束：" + strD);

	}

	@Test
	public void getSEWeekMonth() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date wMdate = sdf.parse("2018-03-05");

		this.sEWeek(wMdate);
		this.sEMonth(wMdate);

	}

	@Test
	public void getLastWeekMonth() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = sdf.parse("2018-11-04");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayofweek == 1) {
			dayofweek += 7;
		}
		cal.add(Calendar.DATE, 2 - dayofweek - 7);

		System.out.println("lastMonth : " + sdf.format(calendar.getTime()));
		System.out.println("lastWeek : " + sdf.format(cal.getTime()));
	}

	@Test
	public void startEndTimeStr() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Long startDateTimeMillis = sdf.parse("2018-10-02").getTime(); // '2018-03-19' AND '2018-03-22'
		Long endDateTimeMillis = sdf.parse("2018-11-19").getTime(); // '2018-03-19' AND '2018-03-22'

		Date startDate = new Date(startDateTimeMillis);
		Date endDate = new Date(endDateTimeMillis);

		String startDateStr = sdf.format(startDate);
		String endDateStr = sdf.format(endDate);

		System.out.print("startTime :  ");
		System.out.print("  " + startDateTimeMillis + "        ");
		System.out.print(startDate + "  ");
		System.out.println("      " + startDateStr + "  ");

		System.out.print("endTime :    ");
		System.out.print("  " + endDateTimeMillis + "        ");
		System.out.print(endDate + "  ");
		System.out.println("      " + endDateStr + "  ");

	}

}
