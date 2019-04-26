package com.clyon.util;
/*
* @Description
* @author Caoxuyang 
* @date 2018年11月13日 下午1:51:48 
*/
public class JudgeDeltaDateUtil {
	
	// 判断统计时间段(日,月)
		public String judgeDeltaDate(long startTime, long endTime) {

			long orderdeltaTime = endTime - startTime;
			long threeMonsTime = 8035200000L; // 1000*60*60*24*93

			// 查询时间为一天
			if (0 <= orderdeltaTime && orderdeltaTime < 86400000) {

				return "ETD";

				// 查询时间小于3个月
			} else if (86400000 <= orderdeltaTime && orderdeltaTime < threeMonsTime) {

				return "GTM";

				// 查询时间大于3个月
			} else if (orderdeltaTime > threeMonsTime) {

				return "LTM";

			} else {

				return "ErrorTime!";

			}

		}

		

}
