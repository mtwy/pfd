package org.loong.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	

	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd hh:mm:ss";
	public static final String YYYYMMDD = "yyyy-MM-dd";

	/**
	 * 把日期date往后增加day天.整数往后推,负数往前移动
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, int day) {

		if (date == null) {
			return null;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);// 把日期往后增加day天.整数往后推,负数往前移动
		return new Date(calendar.getTime().getTime());
	}

	/**
	 * 日期转字符串
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2String(Date date, String format) {

		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 字符串转日期
	 *
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date string2Date(String str, String format) {

		if (StringUtils.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
}
