package com.clyon.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.util.StringUtils;

public class StringUtil extends StringUtils {

	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else if ("null".equalsIgnoreCase(str)) {
			return true;
		} else {
			str = str.trim();
			if (str.equals("")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {
		if (null != str && !"".equals(str) && !"".equals(trimToEmpty(str)) && !"null".equalsIgnoreCase(str)
				&& !"undefined".equalsIgnoreCase(str)) {
			return true;
		}
		return false;
	}

	private static String trimToEmpty(String src) {
		if (isEmpty(src))
			return "";
		return src.trim();
	}

	/**
	 * 从发码记录的router字段里面提取实例+db和表名
	 * 
	 * @param router
	 * @return
	 */
	public static String[] extractRouter(String router) {
		String[] strs = router.split("\\|");
		String[] result = new String[2];
		if (strs != null && strs.length == 3) {
			result[0] = new StringBuilder(strs[0]).append("|").append(strs[1]).toString();
			result[1] = strs[2];
		}
		return result;
	}

	/**
	 * 二进制转字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * jdbcTemple 使用in的时候参数传list
	 * 
	 * @param ids
	 * @return
	 */
	public static List<String> asList(String ids) {
		return asList(ids, ",");
	}

	/**
	 * 拆分字符串
	 * 
	 * @param ids  以同一个字符分隔开的字符串
	 * @param sign 分隔字符
	 * @return
	 */
	public static List<String> asList(String ids, String sign) {
		List<String> idList = new LinkedList<String>();
		if (StringUtil.isNotEmpty(ids)) {
			String idGroup[] = ids.split(sign);
			for (String id : idGroup) {
				if (StringUtil.isNotEmpty(id)) {
					idList.add(id.trim());
				}
			}
		}
		return idList;
	}

	/**
	 * 转成 List<Integer>
	 * 
	 * @param ids  以同一个字符分隔开的字符串
	 * @param sign 分隔字符
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @author yeyi 2017年8月28日
	 */
	public static List<Integer> asListInt(String ids, String sign) {
		List<Integer> idList = new LinkedList<>();
		if (StringUtil.isNotEmpty(ids)) {
			String idGroup[] = ids.split(sign);
			for (String id : idGroup) {
				if (StringUtil.isNotEmpty(id)) {
					idList.add(Integer.parseInt(id.trim()));
				}
			}
		}
		return idList;
	}

	// public static String join(List<String> strLIst) {
	// String str = "";
	// if(CollectionUtil.isNotEmpty(strLIst) ){
	// for(String s: strLIst) {
	// str += s + ",";
	// }
	// str = str.substring(0,str.length()-1);
	// }
	// return str;
	// }
	/**
	 * 把list转成以逗号分隔的字符串
	 * 
	 * @param stringList
	 * @return
	 */
	public static String listToString(List<Integer> stringList) {
		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (Integer string : stringList) {
			if (flag) {
				result.append(",");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}

	/**
	 * @author zzj 去掉样式内容显示纯文本
	 * @param htmlStr
	 * @return
	 */
	public static String filterHtml(String htmlStr) {
		if (!StringUtil.isEmpty(htmlStr)) {
			htmlStr = htmlStr.replaceAll("</?[^>]+>", ""); // 剔出<html>的标签
			htmlStr = htmlStr.replaceAll("<a>\\s*|\t|\r|\n</a>", "");// 去除字符串中的空格,回车,换行符,制表符
		}
		return htmlStr;
	}

	/**
	 * @author zzj 获取图片的url，去掉签名的id 568649|http://192.168.5.250:8080/group2/M00/06
	 *         /9D/wKgF-lWI0CuAYHdTAAAZSK-fhmg540.bmp
	 * @param imageUrl
	 * @return
	 */
	public static String getRealImgUrl(String imageUrl) {
		if (StringUtil.isEmpty(imageUrl))
			return "";
		return imageUrl.substring(imageUrl.indexOf("|") + 1, imageUrl.length());
	}

	/**
	 * 
	 * @Title: getOneImgUrl @Description: (获取一个图片URL) @author zzj @throws
	 */
	public static String getOneImgUrl(String imageUrl) {
		if (StringUtil.isEmpty(imageUrl))
			return "";
		String[] urls = imageUrl.split(";");
		return getRealImgUrl(urls[0]);
	}

	/**
	 * TODO 场景: 多个参数问题校验问题是否为空的遍历 <br>
	 * 
	 * @Description:
	 * @param str
	 * @return
	 */
	public static boolean isEmptyForValiManyParam(String... str) {
		for (String oneString : str) {
			if (oneString == null) {
				return true;
			} else if ("null".equalsIgnoreCase(oneString)) {
				return true;
			} else {
				oneString = oneString.trim();
				if (oneString.equals("")) {
					return true;
				}
			}
		}

		return false;

	}

	/**
	 * 用逗号分隔的字符串转成数字数组
	 */
	public static List<Integer> asIntegerList(String ids) {
		List<Integer> idList = new LinkedList<Integer>();
		if (StringUtil.isNotEmpty(ids)) {
			String idGroup[] = ids.split(",");
			for (String id : idGroup) {
				if (StringUtil.isNotEmpty(id)) {
					idList.add(Integer.parseInt(id.trim()));
				}
			}
		}
		return idList;
	}

	/**
	 * 
	 * <一句话功能简述> 生成四位随机数 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @author zoujingwei 2017年8月15日
	 */
	public static String getFourRandom() {
		Random random = new Random();
		String fourRandom = random.nextInt(10000) + "";
		int randLength = fourRandom.length();
		if (randLength < 4) {
			for (int i = 1; i <= 4 - randLength; i++)
				fourRandom = "0" + fourRandom;
		}
		return fourRandom;
	}

	/**
	 * <p>
	 * Checks if a String is whitespace, empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param str the String to check, may be null
	 * @return <code>true</code> if the String is null, empty or whitespace
	 * @since 2.0
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * <一句话功能简述> 字符串转换数值 <功能详细描述>
	 * 
	 * @param str
	 * @param split
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @author zoujingwei 2017年8月14日
	 */
	public static List<Integer> splitStr(String str, String split) {
		List<Integer> ids = new ArrayList<Integer>();
		if (StringUtil.isNotEmpty(str)) {
			String[] splitIds = str.split(split);
			for (String s : splitIds) {
				Integer id;
				try {
					id = Integer.valueOf(s);
					ids.add(id);
				} catch (NumberFormatException e) {

				}
			}
		}
		return ids;
	}

	/**
	 * 判断空、超出长度，指定返回错误提示
	 * 
	 * @param str
	 * @param length
	 * @param nullMsg
	 * @param limitMsg
	 * @return
	 */
	public static String limitAndNotNull(String str, int length, String nullMsg, String limitMsg) {

		if (str == null) {

			return nullMsg;

		} else if ("null".equalsIgnoreCase(str)) {

			return nullMsg;
		}

		str = str.trim();

		if (str.equals("")) {

			return nullMsg;
		}

		if (str.length() > length) {

			return String.format(limitMsg, length);
		}

		return null;
	}

	public static String generateMerchantCode(int merchantId) {
		return String.format("%06d", Math.abs(merchantId)) + String.valueOf(new Random().nextInt(90) + 10);
	}
}
