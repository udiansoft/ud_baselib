package com.udiansoft.util;

import java.awt.Font;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.UIManager;

public class StrTools {

	public StrTools() {
	}

	public static final long LONG_DAY = 86400000l;

	public static final long LONG_HOUR = 3600000l;

	public static String getMd5(String _str) {
		return MD5utils.MD5Encode(_str).toUpperCase();
	}

	public static Time str2Time(String str) {
		Timestamp t = getCurrentTimestamp();
		Time time = new Time(t.getTime());
		return time;
	}

	public static String getCurrentDateCN(long times) {
		Date date = new Date(times);
		DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.LONG);
		return fullDateFormat.format(date);
	}

	public static Timestamp str2Timestamp(String str) {
		Timestamp t = getCurrentTimestamp();
		if (isNull(str))
			str = t.toString();
		try {
			// 2003-05-01
			if (str.trim().length() <= 10) {
				str = str.substring(0);
				java.sql.Date d = str2Date(str.trim());
				t = new Timestamp(d.getTime());
				// System.out.println(t);
			} else {
				// 2003-05-01 12
				// 2003-05-01 12:00
				// 2003-05-01 12:00:00
				String[] dd = strSplite(str, " ", 2);
				if (dd[1] == null || dd[1].equals("") || dd[1].equals(" ")) {
					dd[1] = "00:00:00";
				}
				if (dd[1].indexOf(":") < 0) {
					dd[1] = dd[1] + ":";
				}
				String[] time = strSplite(dd[1], ":");
				String[] _time = { "00", "00", "00" };
				if (time.length >= 3) {
					_time[0] = time[0];
					_time[1] = time[1];
					_time[2] = time[2];
				} else if (time.length == 2) {
					_time[0] = time[0];
					_time[1] = time[1];
					_time[2] = "00";
				} else if (time.length == 1) {
					_time[0] = time[0];
					_time[1] = "00";
					_time[2] = "00";
				} else {
					_time[0] = dd[1];
					_time[1] = "00";
					_time[2] = "00";
				}
				t = Timestamp.valueOf(dd[0].trim() + " " + _time[0].trim()
						+ ":" + _time[1].trim() + ":" + _time[2].trim());
			}
		} catch (Exception e) {
			if (str.length() >= 10) {
				String dStr = str.substring(0, 10);
				java.sql.Date d = str2Date(dStr.trim());
				t = new Timestamp(d.getTime());
			}
			e.printStackTrace();
		}
		return t;
	}

	public static boolean isNull(String str) {
		boolean reB = false;
		// str = str.trim();
		if (str == null || str.trim().equals("") || str.equals("null")
				|| str.equals("NULL")) {
			return true;
		}
		return reB;
	}

	public static String UpperCaseOne(String str) {
		String aa = str.substring(0, 1);
		return aa.toUpperCase() + str.substring(1);
	}

	public static String getRand() {
		long iii = (new java.util.Date()).getTime();
		return Long.toString(iii);
	}

	public static String int2Str(int ii) {
		try {
			return Integer.toString(ii);
		} catch (Exception ex) {
			return "0";
		}
	}

	public static String float2Str(float ii) {
		try {
			return Float.toString(ii);
		} catch (Exception ex) {
			return "0";
		}
	}

	public static String double2Str(double dd) {
		try {
			return Double.toString(dd);
		} catch (Exception ex) {
			return "";
		}
	}

	public static String double2Str(double dd, int _dot) {
		try {
			String str = NumberTool.double2Str(dd, _dot);
			int dotsite = str.indexOf(".");
			if (dotsite < 0) {
				str += ".";
				int ii = _dot;
				while (ii > 0) {
					str += "0";
					ii--;
				}
			} else {
				String strsub = str.substring(dotsite + 1);
				if (strsub.length() < _dot) {
					int ii = _dot - strsub.length();
					while (ii > 0) {
						str += "0";
						ii--;
					}
				}
			}
			return str;
		} catch (Exception ex) {
			return "";
		}
	}

	public static boolean checkStrNull(String _str) {
		if (_str == null || _str.equals("") || _str.equals("null"))
			return true;
		else
			return false;
	}

	public static int str2Int(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception ex) {
			return 0;
		}

	}

	public static float str2Float(String str) {
		try {
			return Float.parseFloat(str);
		} catch (Exception ex) {
			return 0;
		}

	}

	public static long str2Long(String ii) {
		try {
			return Long.parseLong(ii);
		} catch (Exception ex) {
			return 0;
		}
	}

	public static double str2Double(String str) {
		try {
			return Double.parseDouble(str);
		} catch (Exception ex) {
			return 0;
		}

	}

	public static String GB2U(String strIn) {
		byte[] b;
		String strOut = null;
		if (strIn == null || (strIn.trim()).equals(""))
			return strIn;
		try {
			b = strIn.getBytes("GB2312");
			strOut = new String(b, "ISO8859_1");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.toString());
		}
		return (strOut);
	}

	public static String U2GB(String strIn) {
		String strOut = null;
		String strDefault = "";
		if (strIn == null || strIn.length() == 0)
			return strDefault;
		try {
			byte[] b = strIn.getBytes("ISO8859_1");
			strOut = new String(b, "GB2312");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return strOut;
	}
	public static String U2UTF8(String strIn) {
		String strOut = null;
		String strDefault = "";
		if (strIn == null || strIn.length() == 0)
			return strDefault;
		try {
			byte[] b = strIn.getBytes("ISO8859_1");
			strOut = new String(b, "UTF-8");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return strOut;
	}
	public static String UTF82U(String strIn) {
		String strOut = null;
		String strDefault = "";
		if (strIn == null || strIn.length() == 0)
			return strDefault;
		try {
			byte[] b = strIn.getBytes("UTF-8");
			strOut = new String(b, "ISO8859_1");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return strOut;
	}
	
	//将中文转换成udicode编码
	public static String gbEncoding(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
		System.out.println("unicodeBytes is: " + unicodeBytes);
		return unicodeBytes;
	}	
	
    //将udicode编码转换成中文
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}
	

	public static String[] strSplite(String str, String splite) {
		String[] reStr = new String[3];
		StringTokenizer strToken = new StringTokenizer(str, splite);
		int i = 0;
		while (strToken.hasMoreTokens()) {
			String t = strToken.nextToken();
			// Log.println(t);
			reStr[i++] = t;
		}
		return reStr;
	}

	public static String[] strSplite(String str, String splite, int count) {
		if (count <= 0)
			return null;
		String[] reStr = new String[count];
		StringTokenizer strToken = new StringTokenizer(str, splite);
		int i = 0;
		while (strToken.hasMoreTokens() && i < count) {
			String t = strToken.nextToken();
			// Log.println(t);
			reStr[i++] = t;
		}
		return reStr;
	}

	public static String[] strSplites(String str, String splite) {
		StringTokenizer strToken = new StringTokenizer(str, splite);
		int count = strToken.countTokens();
		String[] reStr = new String[count];
		int i = 0;
		while (strToken.hasMoreTokens() && i < count) {
			String t = strToken.nextToken();
			reStr[i++] = t;
		}
		return reStr;
	}

	// 转换到编辑器内容字串 后台管理
	public static String str2EditHtml(String textinfo) {
		// String s = StrTools.replace(textinfo, "\\", "\\\\"); //&lt;&gt;
		// String s = StrTools.replace(textinfo, "<", "&lt");
		// s = StrTools.replace(s, ">", "&gt");
		String s = StrTools.replace(textinfo, "\"", "\\\"");
		s = StrTools.replace(s, "'", "&acute;");
		s = StrTools.replace(s, "\n", "\\n");
		s = StrTools.replace(s, "\r", "\\r");
		return s;
	}

	// 转换到编辑器内容字串 前台BBS
	public static String str2EditHtmlBBS(String textinfo) {
		// String s = StrTools.replace(textinfo, "\\", "\\\\"); //&lt;&gt;
		String s = StrTools.replace(textinfo, "<", "&lt");
		s = StrTools.replace(s, ">", "&gt");
		s = StrTools.replace(s, "\"", "\\\"");
		s = StrTools.replace(s, "'", "&acute;");
		s = StrTools.replace(s, "\n", "\\n");
		s = StrTools.replace(s, "\r", "\\r");
		return s;
	}

	// 转换到编辑内容到数据库
	public static String str2Content(String textinfo) {
		// String s = StrTools.replace(textinfo, "\"", "\\\"");
		String s = StrTools.replace(textinfo, "'", "&acute;");
		s = StrTools.replace(s, "·", "&bull;");
		s = StrTools.replace(s, "―", "&mdash;");
		s = StrTools.replace(s, "—", "&mdash;");
		// s = StrTools.replace(s, "\n", "\\n");
		// s = StrTools.replace(s, "\r", "\\r");
		// return s;
		// String s = StrTools.replace(textinfo, "\"", "\\\"");
		// String s = StrTools.replace(textinfo, "'", "&acute;");
		// s = StrTools.replace(s, "\n", "\\n");
		// s = StrTools.replace(s, "\r", "\\r");
		return s;
	}

	// 转换到编辑器内容字串
	public static String str2EditContent(String textinfo) {
		String s = StrTools.replace(textinfo, "\"", "\\\"");
		s = StrTools.replace(s, "'", "&acute;");
		s = StrTools.replace(s, "\n", "\\n");
		s = StrTools.replace(s, "\r", "\\r");
		return s;
	}

	// 转换到页面输出
	public static String getContentHtml(String content) {
		content = replace(content, "  ", " &nbsp;");
		content = replace(content, "\r", "<br>");
		return content;
	}

	public static java.sql.Date str2Date(String str) {
		try {
			str = str.replace('/', '-');
			java.sql.Date dt = null;
			if (str == null || str.equals("")) {
				return null;
			} else {
				try {
					dt = java.sql.Date.valueOf(str);
				} catch (Exception e) {
					dt = null;
				}
			}
			return dt;
		} catch (Exception e) {
			return java.sql.Date.valueOf("2003-01-01");
		}
	}

	public static String str4date(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String str=df.format(date);
		return str;
	}
	public static String displayTime() {
		String reDate = "";
		Date date = new Date();

		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(
				DateFormat.FULL, DateFormat.FULL);
		reDate = fullDateFormat.format(date);
		return reDate;
	}

	public static java.sql.Timestamp getCurrentTimestamp() {
		java.util.Date d = new java.util.Date();
		java.sql.Timestamp reD = new java.sql.Timestamp(d.getTime());
		return reD;
	}

	public static java.sql.Timestamp getCurrentTimestamp(int years) {
		java.util.Date d = new java.util.Date();
		d.setYear(d.getYear() + years);
		java.sql.Timestamp reD = new java.sql.Timestamp(d.getTime());
		return reD;
	}

	public static long getCurrentDateLong() {
		java.util.Date d = new java.util.Date();
		return d.getTime();
	}

	public static long getCurrentDateLong(java.sql.Timestamp t) {
		return t.getTime();
	}

	public static String getCurrentDateStr() {
		return int2DateTime(getCurrentDateLong());
	}

	// 20000102
	public static String getCurrentDate(long date) {
		java.util.Date d = new java.util.Date(date);
		String year = int2Str(d.getYear() + 1900);
		int _month = d.getMonth() + 1;
		String month = (_month >= 10 ? int2Str(_month) : "0" + int2Str(_month));
		String day = (d.getDate() >= 10 ? int2Str(d.getDate()) : "0"
				+ int2Str(d.getDate()));
		return year + month + day;
	}
	// 2000-01-02
	public static String getCurrentDateShow(long date) {
		java.util.Date d = new java.util.Date(date);
		String year = int2Str(d.getYear() + 1900);
		int _month = d.getMonth() + 1;
		String month = (_month >= 10 ? int2Str(_month) : "0" + int2Str(_month));
		String day = (d.getDate() >= 10 ? int2Str(d.getDate()) : "0"
				+ int2Str(d.getDate()));
		return year +"-"+ month +"-"+  day;
	}
	public static String getCurrentDateTime() {
		java.util.Date d = new java.util.Date();
		String year = int2Str(d.getYear() + 1900);
		int _month = d.getMonth() + 1;
		String month = (_month >= 10 ? int2Str(_month) : "0" + int2Str(_month));
		String day = (d.getDate() >= 10 ? int2Str(d.getDate()) : "0"
				+ int2Str(d.getDate()));
		String hours = (d.getHours() >= 10 ? int2Str(d.getHours()) : "0"
				+ int2Str(d.getHours()));
		return year + month + day + hours;
	}

	public static java.sql.Date getCurrent() {
		java.util.Date d = new java.util.Date();
		java.sql.Date reD = new java.sql.Date(d.getTime());
		return reD;
	}

	public static String int2DateTime(int dateInt) {
		java.util.Date d = new java.util.Date(dateInt);
		return d.toLocaleString();
	}

	public static String int2DateTime(long dateInt) {
		java.util.Date d = new java.util.Date(dateInt);
		return d.toLocaleString();
	}

	public static String int2DateTime(String dateInt) {
		java.util.Date d = new java.util.Date(str2Int(dateInt));
		return d.toLocaleString();
	}

	public static String int2DateTime(Object dateInt) {
		return int2DateTime(dateInt.toString());
	}

	public static String getRandomId() {
		java.util.Calendar c = new java.util.GregorianCalendar();
		c.setTime(new Date());
		Random rand = new Random();
		int randint = 100 + rand.nextInt(100);
		String reStr = "ID" + c.get(GregorianCalendar.YEAR)
				+ (c.get(GregorianCalendar.MONTH) + 1)
				+ c.get(GregorianCalendar.DAY_OF_MONTH)
				+ c.get(GregorianCalendar.HOUR_OF_DAY)
				+ c.get(GregorianCalendar.MINUTE)
				+ c.get(GregorianCalendar.SECOND) + randint;
		return reStr;
	}

	public static String getRandomPass() {
		Random rand = new Random();
		int randint = 1000000000 + rand.nextInt(100000000);
		String reStr = "" + randint;
		return reStr;
	}

	/*
	 * //Weblogic 7.0 public static String requestStr(String str) { //return
	 * StrTools.U2GB(str.trim()); if(str==null) return null; return str; }
	 * public static String requestStrJSP(String str) { if(str==null) return
	 * null; //Weblogic 6.0 //return StrTools.U2GB(str.trim()); //Weblogic7.0
	 * return StrTools.U2GB(str); } public static String DB2JSP(String str) {
	 * if(str==null) return null; //Weblogic 6.0 //return
	 * StrTools.U2GB(str.trim()); //Weblogic 7.0 return str; }
	 */
	// Weblogic 6.0
	public static String requestStr(String str) {
		// return StrTools.U2GB(str.trim());
		if (str == null)
			return "";
		return str;
		// return StrTools.GB2U(str.trim());
	}

	public static String app2DB(String str) {
		// return StrTools.U2GB(str.trim());
		if (str == null)
			return "";
		return str;
		// return StrTools.GB2U(str.trim());
	}

	public static String requestStrJSP(String str) {
		if (str == null)
			return null;
		return StrTools.U2GB(str.trim());
		// return str;
	}

	public static String DB2JSP(String str) {
		if (str == null)
			return null;
		// Weblogic Oracle Driver
		// return StrTools.U2GB(str.trim());
		// Oracle Driver
		return str;
	}

	// end

	public static final String replace(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	public static String firstUpperCase(String str) {
		if (str.length() > 1) {
			String ss0 = str.substring(0, 1).toUpperCase();
			String ss1 = str.substring(1);
			str = ss0 + ss1;
		}
		return str;
	}

	public static String str2Str(String str) {
		if (str == null || str.equals("null")) {
			return "";
		}
		return str;
	}

	public static String strSub(String str, int length) {
		if (str == null)
			return "";
		int _length = str.length();
		if (_length <= length)
			return str;
		else
			return str.substring(0, length);
	}

	public static String getStringS(ArrayList list, String split) {
		Iterator it = list.iterator();
		String re = "";
		try {
			while (it.hasNext()) {
				String str = (String) it.next();
				if (str != null) {
					re = re + split + str;
				}
			}
		} catch (Exception e) {

		}
		return re;
	}

	public static String getStringS(String[] str, String split) {
		int i = 0;
		String re = "";
		try {
			if (str == null)
				return "";
			while (i < str.length) {
				if (str[i] != null) {
					if (re.equals(""))
						re = re + str[i];
					else
						re = re + split + str[i];
				}
				i++;
			}
		} catch (Exception e) {

		}
		return re;
	}

	public static String getStringS(String[] str) {
		int i = 0;
		String re = "";
		try {
			if (str == null)
				return "";
			while (i < str.length) {
				if (str[i] != null) {
					if (re.equals(""))
						re = re + str[i];
					else
						re = re + "," + str[i];
				}
				i++;
			}
		} catch (Exception e) {

		}
		return re;
	}

	public static boolean findString(String all, String sub) {
		if (all != null && sub != null) {
			if (all.indexOf(sub) >= 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public static String subString(String str, int length) {
		String reStr = null;
		// System.out.print(length);
		if (str == null)
			return "";

		try {
			byte[] strB = str.getBytes();

			if (strB.length <= length)
				return str;

			int i = 0;
			while (i < strB.length) {
				int i1 = 0x00ff & strB[i];
				int i2 = 0x00ff & strB[i + 1];
				if (isCN(i1, i2)) {
					i++;
				}
				i++;
				if (i >= length) {
					break;
				}
			}

			if (i > length) {
				length--;
			}

			byte[] newStrB = new byte[length];
			for (int ii = 0; ii < length; ii++) {
				newStrB[ii] = strB[ii];
			}
			return new String(newStrB, "GB2312");
		} catch (Exception e) {
			reStr = "";
		}
		return reStr;
	}

	public static void writeFile(String _fileName, String str) {
		try {
			java.io.PrintWriter out = new java.io.PrintWriter(
					new java.io.FileOutputStream(_fileName), true);
			out.print(str);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean isCN(int i1, int i2) {
		boolean rt = false;
		// System.out.println("Test:"+i1 +" "+i2);
		if ((i1 >= 161) && (i1 <= 247) && (i2 >= 161) && (i2 <= 254)) {
			rt = true;
		} else {
			rt = false;
		}
		return rt;
	}

	public static void setUIFont(Font font) {
		UIManager.put("Button.font", font);
		UIManager.put("ToggleButton.font", font);
		UIManager.put("RadioButton.font", font);
		UIManager.put("CheckBox.font", font);
		UIManager.put("ColorChooser.font", font);
		UIManager.put("ToggleButton.font", font);
		UIManager.put("ComboBox.font", font);
		UIManager.put("ComboBoxItem.font", font);
		UIManager.put("InternalFrame.titleFont", font);
		UIManager.put("Label.font", font);
		UIManager.put("List.font", font);
		UIManager.put("MenuBar.font", font);
		UIManager.put("Menu.font", font);
		UIManager.put("MenuItem.font", font);
		UIManager.put("RadioButtonMenuItem.font", font);
		UIManager.put("CheckBoxMenuItem.font", font);
		UIManager.put("PopupMenu.font", font);
		UIManager.put("OptionPane.font", font);
		UIManager.put("Panel.font", font);
		UIManager.put("ProgressBar.font", font);
		UIManager.put("ScrollPane.font", font);
		UIManager.put("Viewport", font);
		UIManager.put("TabbedPane.font", font);
		UIManager.put("Table.font", font);
		UIManager.put("TableHeader.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("PasswordFiled.font", font);
		UIManager.put("TextArea.font", font);
		UIManager.put("TextPane.font", font);
		UIManager.put("EditorPane.font", font);
		UIManager.put("TitledBorder.font", font);
		UIManager.put("ToolBar.font", font);
		UIManager.put("ToolTip.font", font);
		UIManager.put("Tree.font", font);
	}

	// 2004-6-11 13:37:21
	public static String getDateTime(long _date) {
		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(
				DateFormat.MEDIUM, DateFormat.MEDIUM);
		return fullDateFormat.format(new Date(_date));
	}

	// 2004-6-11
	public static String getDate(Date _date) {
		DateFormat fullDateFormat = DateFormat
				.getDateInstance(DateFormat.MEDIUM);
		return fullDateFormat.format(_date);
	}
	
	public static String fmtDate(Date _date,String fmt) {
		SimpleDateFormat sdf=new SimpleDateFormat(fmt);
		return sdf.format(_date);
	}
	
	// 2004-6-11
	public static String getDate(long _date) {
		DateFormat fullDateFormat = DateFormat
				.getDateInstance(DateFormat.MEDIUM);
		return fullDateFormat.format(new Date(_date));
	}

	// 2004年6月11日
	public static String getDateCN(long _date) {
		DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.LONG);
		return fullDateFormat.format(new Date(_date));
	}

	// 2004年6月11日 星期五
	public static String getDateWeekCN(long _date) {
		DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.FULL);
		return fullDateFormat.format(new Date(_date));
	}

	public static String encode(String _str) {
		return Base64.encode(_str);
	}

	public static String decode(String _str) {
		return Base64.decode(_str);
	}


	/**
	 * 判断IP地址大小 > return 1 < return 2 = return 0 error return -1
	 */

	public static int compareIp(String ipOne, String ipTwo) {
		String[] ipListOne = ipOne.split(".");
		String[] ipListTwo = ipTwo.split(".");
		if (ipListOne.length == 4 && ipListTwo.length == 4) {
			for (int i = 0; i < 4; i++) {
				if (str2Int(ipListOne[i]) > str2Int(ipListTwo[i])) {
					return 1;
				} else if (str2Int(ipListOne[i]) < str2Int(ipListTwo[i])) {
					return 2;
				}
			}
			return 0;
		}
		return -1;
	}

	public static int[] getIps(String ip) {
		int[] ips = new int[4];
		String[] _ips = strSplites(ip, ".");
		for (int i = 0; i < _ips.length; i++) {
			ips[i] = str2Int(_ips[i]);
		}
		return ips;
	}

    //将传入字串的每位数字分别乘以相应的校验系数，所得积求和后模10，结果即为校验位值。 
    //校验系数：左起奇数位为“3”，偶数位为“8”
	public static int getCheckCode(String strIn) {
		int s = 0;
		for(int i=0;i<strIn.length();i++) {
			if(i%2 == 0) {
				s = s + Integer.parseInt(strIn.substring(i,i+1))*3;
			} else {
				s = s + Integer.parseInt(strIn.substring(i,i+1))*8;
			}
			
		}

        int c = s%10;
		
		return c;
	}
	
	public static String int2SpecifiedLengthStr(int intIn,int length) {
		String ii = String.valueOf(intIn);
		String zero = "000000000000000000000000000000";
		if(ii.length() < length) {
			ii =  zero.substring(0,length-ii.length()) + ii;
		}
		
		return ii;
	}
	
	//six取值范围：1~8，eix取值范围：3~9
	public static String genRandNo(int six, int eix) {
		Random rand = new Random();
		Date curd = new Date();
		String randno = int2SpecifiedLengthStr(rand.nextInt(),8).substring(six,eix) + DateUtil.getDateTimeStr(curd).substring(17, 19) + DateUtil.getDateTimeStr(curd).substring(8, 10);
		return randno;
	}

	public static void main(String[] args) {
		String aa = "0123".substring(0,0);
		System.out.println("--- aa:[" + aa + "]");
		
		String test = gbEncoding("中国");
		System.out.println("--- test:" + test);
		
        System.out.println("--------------- cardno:" + genRandNo(2,6));
		Random rand = new Random();
		int r = rand.nextInt(10);
        System.out.println("--------------- r:" + r);
	}
}
