package com.udiansoft.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {
	
	/**
	 * 获取当前系统时间
	 * @return
	 */
	public static long getTime() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 获取当前系统日期
	 * @return
	 */
	public static Date getDate() {
		Date date = new Date(System.currentTimeMillis());
		return date;
	}
	
	public static String getDateString() {
		Date date = new Date(System.currentTimeMillis());
		String cur_date = new SimpleDateFormat("yyyy-MM-dd").format(date);
		return cur_date;
	}
	
	public static String getCompactDateString() {
		Date date = new Date(System.currentTimeMillis());
		String cur_date = new SimpleDateFormat("yyyyMMdd").format(date);
		return cur_date;
	}
	
	public static String getCompact6charDateStr() {
		Date date = new Date(System.currentTimeMillis());
		String cur_date = new SimpleDateFormat("yyMMdd").format(date);
		return cur_date;
	}
	
	public static String getDateTimeString() {
		Date date = new Date(System.currentTimeMillis());
		String cur_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		return cur_date;
	}
	
	public static String getDateTimeStringByFormat(String format) {
		Date date = new Date(System.currentTimeMillis());
		String cur_date = new SimpleDateFormat(format).format(date);
		return cur_date;
	}
	
	public static String getYearString() {
		Date date = new Date(System.currentTimeMillis());
		String cur_date = new SimpleDateFormat("yyyy").format(date);
		return cur_date;
	}
	
	public static String getMonthString() {
		Date date = new Date(System.currentTimeMillis());
		String cur_date = new SimpleDateFormat("MM").format(date);
		return cur_date;
	}

	public static String getDayString() {
		Date date = new Date(System.currentTimeMillis());
		String cur_date = new SimpleDateFormat("dd").format(date);
		return cur_date;
	}

	public static String getTimeString() {
		Date date = new Date(System.currentTimeMillis());
		String cur_date = new SimpleDateFormat("HH:mm:ss").format(date);
		return cur_date;
	}
	
	public static String getCompactTimeString() {
		Date date = new Date(System.currentTimeMillis());
		String cur_date = new SimpleDateFormat("HHmmss").format(date);
		return cur_date;
	}
	
	public static String getDateStringWithSeconds() {
		Date date = new Date(System.currentTimeMillis());
		String cur_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		return cur_date;
	}
	
	public static String getDateStringWithMillis() {
		Date date = new Date(System.currentTimeMillis());
		String cur_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(date);
		return cur_date;
	}
	
	public static void main(String[] args) {
		System.out.println(getDateTimeStringByFormat("yyyyMMdd"));
		Float dd = -9.88f;
		System.out.println(dd.intValue());
	}
}
