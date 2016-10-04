package com.udiansoft.util;

import java.text.*;
import java.util.*;

public class DateTool {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      //时间格式
	private static String[] formatStr = {"yyyy-MM-dd",
		                                 "yyyy/MM/dd",
		                                 "yyyy.MM.dd",
		                                 "yyyyMMdd",
		                                 "yyyy-MM-dd HH:mm:ss",
                                         "yyyy/MM/dd HH:mm:ss",
		                                 "yyyy.MM.dd HH:mm:ss",
		                                 "yyyyMMdd HH:mm:ss",
		                                 "yyyy-MM-dd hh:mm:ss",
                                         "yyyy/MM/dd hh:mm:ss",
		                                 "yyyy.MM.dd hh:mm:ss",
		                                 "yyyyMMdd hh:mm:ss",
                                         "EEE MMM dd HH:mm:ss 'UTC 0800' yyyy",
                                         "EEE MMM dd HH:mm:ss 'CST' yyyy",
		                                 "yy-MM-dd HH:mm:ss",
		                                 "yy/MM/dd HH:mm:ss",
		                                 "yy.MM.dd HH:mm:ss",
		                                 "yyMMdd HH:mm:ss",
		                                 "yyyy.MM.dd G 'at' hh:mm:ss z",
		                                 "yyyyMMddHHmmssZ",
		                                 "yyyy.MM.dd G 'at' hh:mm:ss z",
		                                 "yyyy.MM.dd G 'at' hh:mm:ss z"};

    public static Date str2Date(String strDate)  {
    	Date retDate = null;

    	try {
        	DateFormat df = DateFormat.getInstance();
        	retDate = df.parse(strDate);
    	} catch(ParseException e) {
    	    SimpleDateFormat formatter;
    	    for(int i=0; i<formatStr.length; i++) {
    	    	formatter = new SimpleDateFormat(formatStr[i]);
    	    	try {
    	    		retDate = formatter.parse(strDate);
    	    		break;
    	    	} catch(ParseException e2) {
    	    		continue;
    	    	}
    	    }
    	}

	    return retDate;
	} 

    public static Date str2Date(String format,String strDate)  {
    	Date retDate = null;

    	try {
    	    SimpleDateFormat formatter = new SimpleDateFormat(format);
	    	retDate = formatter.parse(strDate);
    	} catch(ParseException e) {
    	}

	    return retDate;
	} 

	public static String formatDate(String fmt,Date oldDate) {
		if(fmt != null && !"".equals(fmt)) {
			sdf = new SimpleDateFormat(fmt); 
		}
		try {
			String mydate = sdf.format(oldDate);
			
			return mydate;
		} catch(Exception e) {
			e.printStackTrace();
		}
        return null;
	}

}
