package com.udiansoft.util;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Time; 
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;


/**
 * <p>
 * Title: Cyberway Commons
 * </p>
 * <p>
 * Description: Common Date Utility
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: Cyberway Compucomm Co., Ltd.
 * </p>
 * 
 * @author Gaven
 * @version 1.0
 */

public class DateUtil {
	/**
	 * getDateStr get a string with format YYYY-MM-DD from a Date object
	 * 
	 * @param date
	 *            date
	 * @return String
	 */
	static public String getDateStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	static public String getDateStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(date);
	}

	static public String getDateStrCompact(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		return str;
	}

	/**
	 * getDateStr get a string with format YYYY-MM-DD HH:mm:ss from a Date
	 * object
	 * 
	 * @param date
	 *            date
	 * @return String
	 */
	static public String getDateTimeStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	static public String getDateTimeStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.format(date);
	}

	public static String getCurDateStr(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}

	static public String getTimeStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(date);
	}

	/**
	 * Parses text in 'YYYY-MM-DD' format to produce a date.
	 * 
	 * @param s
	 *            the text
	 * @return Date
	 * @throws ParseException
	 */
	static public Date parseDate(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(s);
	}

	static public Date parseDate(String s, String f) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(f);
		return format.parse(s);
	}

	static public Date parseDateC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.parse(s);
	}

	/**
	 * Parses text in 'YYYY-MM-DD' format to produce a date.
	 * 
	 * @param s
	 *            the text
	 * @return Date
	 * @throws ParseException
	 */
	static public Date parseDateTime(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.parse(s);
	}

	static public Date parseDateTimeC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.parse(s);
	}

	/**
	 * Parses text in 'HH:mm:ss' format to produce a time.
	 * 
	 * @param s
	 *            the text
	 * @return Date
	 * @throws ParseException
	 */
	static public Date parseTime(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.parse(s);
	}

	static public Date parseTimeC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH时mm分ss秒");
		return format.parse(s);
	}

	static public int yearOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(0, 4));
	}

	static public int monthOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(5, 7));
	}

	static public int dayOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(8, 10));
	}
	
	static public int dayOfWeek(String dstr) throws ParseException {
		Date dat = parseDateTime(dstr);
		return dayOfWeek(dat);
	}
	
	static public int dayOfWeek(Date s) throws ParseException {
		Calendar cal = new GregorianCalendar();
		cal.setTime(s);
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}

	static public String getDateTimeStr(java.sql.Date date, double time) {
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate();
		String dateStr = year + "-" + month + "-" + day;
		Double d = new Double(time);
		String timeStr = String.valueOf(d.intValue()) + ":00:00";

		return dateStr + " " + timeStr;
	}

	/**
	 * Get the total month from two date.
	 * 
	 * @param sd
	 *            the start date
	 * @param ed
	 *            the end date
	 * @return int month form the start to end date
	 * @throws ParseException
	 */
	static public int diffDateM(Date sd, Date ed) throws ParseException {
		return (ed.getYear() - sd.getYear()) * 12 + ed.getMonth()
				- sd.getMonth() + 1;
	}

	static public int diffDateD(Date sd, Date ed) throws ParseException {
		return Math.round((ed.getTime() - sd.getTime()) / 86400000) + 1;
	}

	static public int diffDateM(int sym, int eym) throws ParseException {
		return (Math.round(eym / 100) - Math.round(sym / 100)) * 12
				+ (eym % 100 - sym % 100) + 1;
	}

	static public java.sql.Date getNextMonthFirstDate(java.sql.Date date)
			throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.set(Calendar.DATE, 1);
		return new java.sql.Date(scalendar.getTime().getTime());
	}

	static public Date getNextMonthDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}

	static public java.sql.Date getFrontSqlDateByDayCount(java.sql.Date date, int dayCount) throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.DATE, -dayCount);
		return new java.sql.Date(scalendar.getTime().getTime());
	}
	
	static public Date getFrontDateByDayCount(Date date, int dayCount) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -dayCount);
		return calendar.getTime();
	}
	
	static public Date getFrontDateByMonthCount(Date date, int monthCount) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -monthCount);
		return calendar.getTime();
	}
	
	static public Date getFrontDateByYearCount(Date date, int yearCount) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, -yearCount);
		return calendar.getTime();
	}
	
	static public Date getFrontDateByHourCount(Date date, int hourCount) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, -hourCount);
		return calendar.getTime();
	}
	
	static public Date getFrontDateByMinuteCount(Date date, int minuteCount) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -minuteCount);
		return calendar.getTime();
	}
	
	static public Date getFrontDateBySecondCount(Date date, int secondCount) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, -secondCount);
		return calendar.getTime();
	}
	
	public static String getTodayFrontDateStrByDayCount(int dayCount) {
		Date retdate = getTodayFrontDateByDayCount(dayCount);
		return getDateStr(retdate);
	}
	
	public static java.util.Date getTodayFrontDateByDayCount(int dayCount) {
		Date today = new Date();
		return getFrontDateByDayCount(today,dayCount);
	}

	
	/**
	 * Get first day of the month.
	 * 
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @return Date first day of the month.
	 * @throws ParseException
	 */
	static public Date getFirstDay(String year, String month)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	static public Date getFirstDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	static public Date getFirstDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);

		return c.getTime();
	}

	static public Date getLastDay(String year, String month)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.add(Calendar.DATE, -1);
		date = scalendar.getTime();
		return date;
	}

	static public Date getLastDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.add(Calendar.DATE, -1);
		date = scalendar.getTime();
		return date;
	}

	static public Date getLastDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.roll(Calendar.DATE, false);
		return c.getTime();
	}

	/**
	 * getToday get todat string with format YYYY-MM-DD from a Date object
	 * 
	 * @param date
	 *            date
	 * @return String
	 */

	static public String getTodayStr() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		return getDateStr(calendar.getTime());
	}

	static public Date getToday() throws ParseException {
		return new Date(System.currentTimeMillis());
	}

	static public String getTodayAndTime() {
		return new Timestamp(System.currentTimeMillis()).toString();
	}

	static public String getTodayC() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		return getDateStrC(calendar.getTime());
	}

	static public int getThisYearMonth() throws ParseException {
		Date today = Calendar.getInstance().getTime();
		return (today.getYear() + 1900) * 100 + today.getMonth() + 1;
	}

	static public int getYearMonth(Date date) throws ParseException {
		return (date.getYear() + 1900) * 100 + date.getMonth() + 1;
	}

	// 获取相隔年数
	static public int getDistinceYear(String beforedate, String afterdate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");

		int yearCount = 0;
		try {
			java.util.Date d1 = d.parse(beforedate);
			java.util.Date d2 = d.parse(afterdate);
			yearCount = d2.getYear() - d1.getYear();

		} catch (ParseException e) {
			System.out.println("Date parse error!");
			// throw e;
		}
		return yearCount;
	}

	// 获取相隔月数
	static public long getDistinceMonth(String beforedate, String afterdate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		long monthCount = 0;
		try {
			java.util.Date d1 = d.parse(beforedate);
			java.util.Date d2 = d.parse(afterdate);

			monthCount = (d2.getYear() - d1.getYear()) * 12 + d2.getMonth()
					- d1.getMonth();
			// dayCount = (d2.getTime()-d1.getTime())/(30*24*60*60*1000);

		} catch (ParseException e) {
			System.out.println("Date parse error!");
			// throw e;
		}
		return monthCount;
	}

	// 获取相隔月数,精确到小数点后两位
	static public double getDistinceMonth1(Date beforedate, Date afterdate)
			throws ParseException {
		double monthCount = 0;
		if (beforedate != null && afterdate != null) {
			try {
				long dayCount = (afterdate.getTime() - beforedate.getTime())
						/ (24 * 60 * 60 * 1000);// 实际相隔天数
				System.out.println("dayCount->" + dayCount);
				monthCount = (dayCount + 1) / 28.0f;
			} catch (Exception e) {
				System.out.println("Date parse error!");
			}
		}
		return AccurateCalculate.round(monthCount, 2);
	}

	// 获取相隔天数
	static public long getDistinceDay(String beforedate, String afterdate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		long dayCount = 0;
		try {
			java.util.Date d1 = d.parse(beforedate);
			java.util.Date d2 = d.parse(afterdate);

			dayCount = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);

		} catch (ParseException e) {
			System.out.println("Date parse error!");
			// throw e;
		}
		return dayCount;
	}

	// 获取相隔天数
	static public long getDistinceDay(Date beforedate, Date afterdate)
			throws ParseException {
		long dayCount = 0;

		try {
			dayCount = (afterdate.getTime() - beforedate.getTime())
					/ (24 * 60 * 60 * 1000);

		} catch (Exception e) {
			// System.out.println("Date parse error!");
			// // throw e;
		}
		return dayCount;
	}

	static public long getDistinceDay(java.sql.Date beforedate,
			java.sql.Date afterdate) throws ParseException {
		long dayCount = 0;

		try {
			dayCount = (afterdate.getTime() - beforedate.getTime())
					/ (24 * 60 * 60 * 1000);

		} catch (Exception e) {
			// System.out.println("Date parse error!");
			// // throw e;
		}
		return dayCount;
	}

	// 获取相隔天数
	static public long getDistinceDay(String beforedate) throws ParseException {
		return getDistinceDay(beforedate, getTodayStr());
	}

	// 获取相隔时间数
	static public long getDistinceTime(String beforeDateTime,
			String afterDateTime) throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timeCount = 0;
		try {
			java.util.Date d1 = d.parse(beforeDateTime);
			java.util.Date d2 = d.parse(afterDateTime);

			timeCount = (d2.getTime() - d1.getTime()) / (60 * 60 * 1000);

		} catch (ParseException e) {
			System.out.println("Date parse error!");
			throw e;
		}
		return timeCount;
	}

	// 获取相隔时间数
	static public long getDistinceTime(String beforeDateTime)
			throws ParseException {
		return getDistinceTime(beforeDateTime, new Timestamp(System
				.currentTimeMillis()).toLocaleString());
	}

	// 获取相隔分钟数
	static public long getDistinceMinute(String beforeDateTime,
			String afterDateTime) throws ParseException {
		SimpleDateFormat d = null;
		if(beforeDateTime.length() > 16) {
			d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			d = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
		long timeCount = 0;
		try {
			java.util.Date d1 = d.parse(beforeDateTime);
			java.util.Date d2 = d.parse(afterDateTime);

			timeCount = (d2.getTime() - d1.getTime()) / (60 * 1000);

		} catch (ParseException e) {
			System.out.println("Date parse error!");
			throw e;
		}
		return timeCount;
	}

	// 获取相隔分钟数
	static public long getDistinceMinuteByFormat(String beforeDateTime,
			String afterDateTime,String dformat) throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("HH:mm");
		long timeCount = 0;
		try {
			java.util.Date d1 = d.parse(beforeDateTime);
			java.util.Date d2 = d.parse(afterDateTime);

			timeCount = (d2.getTime() - d1.getTime()) / (60 * 1000);

		} catch (ParseException e) {
			System.out.println("Date parse error!");
			throw e;
		}
		return timeCount;
	}

	// 获取相隔分钟数
	static public long getDistinceMinute(String afterDateTime)
			throws ParseException {
		return getDistinceMinute(new Timestamp(System.currentTimeMillis())
				.toLocaleString(), afterDateTime);
	}

	// 判断是否超出指定相隔时间范围内
	static public boolean isOvertime(String beforeDateTime, String timeCount) {
		boolean exceed = false;
		try {
			long count1 = Long.parseLong(timeCount);
			long count2 = getDistinceTime(beforeDateTime);
			if (count1 < count2) {
				exceed = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return exceed;
	}

	static public String getTimestamStr(Timestamp timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(timestamp);
	}

	static public String getTimeStr(Time time) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(time);
	}

	// 判断后者时间是否为前者时间前
	static public boolean isBeforeCheckDate(String checkdate,
			java.util.Date auditDate) throws ParseException {
		java.util.Date cd;
		try {
			cd = new java.util.Date(parseDateTime(checkdate).getTime());

		} catch (ParseException ex) {
			System.out.println(ex);
			return false;
		}
		return isBeforeCheckDate(cd, auditDate);
	}

	static private boolean isBeforeCheckDate(java.util.Date checkdate,
			java.util.Date auditDate) throws ParseException {
		return auditDate.before(checkdate);
	}

	static public String format(Date date, String formatText) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(formatText);
		return format.format(date);
	}

	static public int getDaysOfMonth(Date startdate, Date enddate, String month)
			throws Exception {
		int startmonth = startdate.getMonth() + 1;
		int endmonth = enddate.getMonth() + 1;
		int m = Integer.parseInt(month);
		int day = getLastDay(String.valueOf(startdate.getYear()), month)
				.getDate();
		if ((startmonth < m) && (m < endmonth)) {
			return day;
		} else if (m == startmonth) {
			return day - startdate.getDate() + 1;
		} else if (m == endmonth) {
			return enddate.getDate();
		}
		return 0;
	}

	static public int diffDateH(String beforeDateTime, String afterDateTime)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int HourCount = 0;
		try {
			java.util.Date d1 = d.parse(beforeDateTime);
			java.util.Date d2 = d.parse(afterDateTime);
			HourCount = (Math
					.round(((d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000)) * 100)) / 100;
		} catch (ParseException e) {
			System.out.println("Date parse error!");
			throw e;
		}
		return HourCount;
	}

	// /////////vinsun/////////////////
	static public Date getNextDateByYearCount(Date date, int yearCount)
			throws ParseException {
		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date);
		scalendar.add(Calendar.YEAR, yearCount);
		return scalendar.getTime();
	}

	static public Date getNextDateByMonthCount(Date date, int monthCount)
			throws ParseException {
		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, monthCount);
		return scalendar.getTime();
	}

	// /////////////////////////////////

	static public Date getNextDateByDayCount(Date date, int dayCount)
			throws ParseException {
		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date);
		scalendar.add(Calendar.DAY_OF_MONTH, dayCount);
		return scalendar.getTime();
	}

	static public Date getNextDateByMinuteCount(Date date, int minuteCount)
			throws ParseException {
		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date);
		scalendar.add(Calendar.MINUTE, minuteCount);
		return scalendar.getTime();
	}

	/**
	 * 获取两个日期时间的差值,忽略年月日
	 * 
	 * @param beforeTime
	 *            前一个时间
	 * @param afterTime
	 *            后一个时间
	 * @return 时间差值,单位为毫秒(ms)
	 */
	public static long getDiffTime(Date beforeTime, Date afterTime) {
		try {
			String beforeTimeStr = format(beforeTime, "HH:mm:ss");
			String afterTimeStr = format(afterTime, "HH:mm:ss");
			Date bTime = parseTime(beforeTimeStr);
			Date aTime = parseTime(afterTimeStr);
			long diff = aTime.getTime() - bTime.getTime();
			return diff;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 获取两个日期时间的差值,包括年月日
	 * 
	 * @param beforeTime
	 *            前一个时间
	 * @param afterTime
	 *            后一个时间
	 * @return 时间差值,单位为毫秒(ms)
	 */
	public static long getDiffDateTime(Date beforeTime, Date afterTime) {
		try {
			long diff = afterTime.getTime() - beforeTime.getTime();
			return diff;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
	* @author 2015-11-03 下午05:21:49
	* @功能：获取网络最新时间（尝试多个地址）
	* @return
	*/
	public static String getNTPDateTime(){
		String[] urls = {"time.windows.com","cn.pool.ntp.org","time.nist.gov","time-nw.nist.gov","time-a.nist.gov","time-b.nist.gov","210.72.145.44"};
		String dtstr = null;
		for(int i=0; i<urls.length; i++) {
			System.out.println("---- i=" + i);
			try {
			    dtstr = getNTPDateTimeOnce(urls[i]);
			    if(dtstr == null) {
			    	continue;
			    }
			    return dtstr;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return getDateTimeStr(new Date()); 
	}
	
	/**
	* @author 2015-11-03 下午05:21:49
	* @功能：获取网络最新时间（仅获取一次）
	* @return
	*/
	public static String getNTPDateTimeOnce(String url){
		try { 
		    NTPUDPClient timeClient = new NTPUDPClient(); 
		    timeClient.setDefaultTimeout(5000);    //5秒超时
		    InetAddress timeServerAddress = InetAddress.getByName(url);
		    TimeInfo timeInfo = timeClient.getTime(timeServerAddress); 
		    TimeStamp timeStamp = timeInfo.getMessage().getTransmitTimeStamp(); 
		    return getDateTimeStr(timeStamp.getDate()); 
		} catch (UnknownHostException uhe) { 
		    uhe.printStackTrace(); 
		    //return getDateTimeStr(new Date());
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (IOException ioe) { 
			ioe.printStackTrace();
		    //return getDateTimeStr(new Date());
		}
		   
		return null;
	}	

	public static void main(String[] args) {
		try {
			System.out.println("====== ntp time is:" + getNTPDateTime());
			
			Date date = parseDateTime("2014-06-15 15:50:48");
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			System.out.println("------------------- time millis:" + c.getTimeInMillis());
			System.out.println("-------------------cur time millis:" + CurrentTime.getTime());
			if(DateUtil.isBeforeCheckDate(DateUtil.getCurDateStr("yyyy-MM-dd HH:mm:ss"),date)) {
				System.out.println("expired");
			} else {
				System.out.println("not expire..");
			}
			
			String username = "udiantech";
			String mm = MD5utils.MD5Encode(username);
	        String usentry = mm.substring(0,5) + username.charAt(0) + mm.substring(27) + username.charAt(1);
	        System.out.println( usentry);
	        Long tt = 1418087961 * 1000l;
	        System.out.println(new Date(tt));
	        
	        long lll = getDistinceDay("2016-04-13","2016-05-15");
	        System.out.println("------ lll:" + lll);
	        
	        Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	        System.out.println("------ now:" + format.format(now));
	        format = new SimpleDateFormat("ssmmSSSHH");
	        System.out.println("------ now:" + format.format(now));	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
