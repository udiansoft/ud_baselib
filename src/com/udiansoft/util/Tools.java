package com.udiansoft.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class Tools extends StrTools {
	public Tools() {
	}

	public static String str2NoHtml(String str) {
		if (isNull(str))
			return "";
		str = replace(str, "<", "&lt;");
		str = replace(str, ">", "&gt;");
		str = replace(str, "  ", " &nbsp;");
		str = replace(str, "  ", " &nbsp;");
		str = replace(str, "\"", "\\\"");
		str = replace(str, "'", "\\'");
		str = replace(str, "\r", "<br>");
		return str;
	}

	public static String str2NoHtml_BBSIndex(String str) {
		if (isNull(str))
			return "";
		str = replace(str, "<", "&lt;");
		str = replace(str, ">", "&gt;");
		str = replace(str, "  ", " &nbsp;");
		str = replace(str, "  ", " &nbsp;");
		str = replace(str, "\"", "\\\"");
		str = replace(str, "\r", "<br>");
		return str;
	}

	public static String ubb2html(String ubbCode) {
		return ubb2html(ubbCode, true);
	}

	public static String ubb2html(String ubbCode, boolean isHidden) {
		String htmlCode = ubbCode;
		try {
			String[] UBBhead = { "[b", "[i", "[u", "[center", "[url", "[email", "[img", "[flash",
				"[fly", "[move", "[color", "[size", "[face", "[rm", "[mp", "[qt", "[dir", "[glow",
				"[shadow", "[quote", "[sound", "[hide", "[s520face" };
			String[] UBBfoot = { "[/b]", "[/i]", "[/u]", "[/center]", "[/url]", "[/email]",
				"[/img]", "[/flash]", "[/fly]", "[/move]", "[/color]", "[/size]", "[/face]",
				"[/rm]", "[/mp]", "[/qt]", "[/dir]", "[/glow]", "[/shadow]", "[/quote]",
				"[/sound]", "[/hide]", "[/s520face]" };
			for (int i = 0; i < UBBfoot.length; i++) {
				htmlCode = ubbreplace(htmlCode, UBBhead[i], UBBfoot[i], isHidden);
			}

		} catch (Exception e) {
		}

		return htmlCode;
	}

	private static String ubbreplace(String code, String head, String foot, boolean isHidden) {
		int index1 = 0, index2 = 0, index3 = 0, index4 = 0;
		String other = "";
		while (index3 >= 0) {

			index3 = code.indexOf(foot, index3);
			if (index3 < 0) {
				break;
			}
			index4 = index3 + foot.length();
			String leftstr = code.substring(0, index3);
			index1 = leftstr.lastIndexOf(head);
			if (index1 < 0)
				return code;
			String midstr = code.substring(index1, index4);
			index2 = midstr.indexOf("]");
			if (index2 + 1 >= midstr.length()) {
				return code;
			}
			String mid1 = midstr.substring(index2 + 1, index3 - index1);
			leftstr = leftstr.substring(0, index1);
			String rightstr = code.substring(index4);

			if (foot.equals("[/img]")) {
				other = "<a href='" + mid1 + "' target=_blank><img src=\"" + mid1
					+ "\" border=0 onLoad=\"javascript:if(this.width>580)this.width=580;\"></a>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/move]")) {
				
				other = "<MARQUEE scrollamount=3>" + mid1 + "</marquee>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/fly]")) {
				
				other = "<MARQUEE width=90% behavior=alternate scrollamount=3>" + mid1
					+ "</marquee>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/center]")) {
				
				other = "<div align=center>" + mid1 + "</div>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/hide]")) {
				
				other = "<u><font color=red>�ظ��ſ��Բ鿴</font></u>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/url]")) {
				// l��
				String mid0 = midstr.substring(head.length(), index2);
				other = "<a href" + mid0 + " target=_blank>" + mid1 + "</a>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/color]")) {
				
				String mid0 = midstr.substring(head.length(), index2);
				other = "<font color" + mid0 + ">" + mid1 + "</font>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/size]")) {
				
				String mid0 = midstr.substring(head.length(), index2);
				other = "<font size" + mid0 + ">" + mid1 + "</font>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/face]")) {
				
				String mid0 = midstr.substring(head.length(), index2);
				other = "<font face" + mid0 + ">" + mid1 + "</font>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/email]")) {
				// email
				other = "<a href=mailto:" + mid1 + " >" + mid1 + "</a>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/flash]")) {
				// flash
				String mid0 = midstr.substring(head.length(), index2);
				String mid01 = "200";
				String mid02 = "200";
				int index0 = mid0.indexOf(",");
				if (index0 > 0) {
					mid01 = mid0.substring(1, index0);
					mid02 = mid0.substring(index0 + 1);
				}
				other = "<a href="
					+ mid1
					+ " TARGET=_blank><IMG SRC=bbs_images/files/swf.gif border=0 alt=����´������͸�FLASH����! height=16 width=16>[ȫ������]</a><br><OBJECT codeBase=http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=4,0,2,0 classid=clsid:D27CDB6E-AE6D-11cf-96B8-444553540000 width=500 height=400><PARAM NAME=movie VALUE="
					+ mid1
					+ "><PARAM NAME=quality VALUE=high><embed src="
					+ mid1
					+ " quality=high pluginspage='http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash' type='application/x-shockwave-flash' width="
					+ mid01 + " height=" + mid02 + "></embed></OBJECT>";
				code = leftstr + other + rightstr;

			} else if (foot.equals("[/dir]")) {
				// dir
				String mid0 = midstr.substring(head.length(), index2);
				String mid01 = "200";
				String mid02 = "200";
				int index0 = mid0.indexOf(",");
				if (index0 > 0) {
					mid01 = mid0.substring(1, index0);
					mid02 = mid0.substring(index0 + 1);
				}
				other = "<object classid=clsid:166B1BCA-3F9C-11CF-8075-444553540000 codebase=http://download.macromedia.com/pub/shockwave/cabs/director/sw.cab#version=7,0,2,0 width="
					+ mid01
					+ " height="
					+ mid02
					+ "><param name=src value="
					+ mid1
					+ "><embed src="
					+ mid1
					+ " pluginspage=http://www.macromedia.com/shockwave/download/ width="
					+ mid01
					+ " height=" + mid02 + "></embed></object>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/rm]")) {
				// realplayer
				String mid0 = midstr.substring(head.length(), index2);
				String mid01 = "200";
				String mid02 = "200";
				int index0 = mid0.indexOf(",");
				if (index0 > 0) {
					mid01 = mid0.substring(1, index0);
					mid02 = mid0.substring(index0 + 1);
				}

				other = "<OBJECT classid=clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA class=OBJECT id=RAOCX width="
					+ mid01
					+ " height="
					+ mid02
					+ "><PARAM NAME=SRC VALUE="
					+ mid1
					+ "><PARAM NAME=CONSOLE VALUE=Clip1><PARAM NAME=CONTROLS VALUE=imagewindow><PARAM NAME=AUTOSTART VALUE=true></OBJECT><br><OBJECT classid=CLSID:CFCDAA03-8BE4-11CF-B84B-0020AFBBCCFA height=32 id=video2 width="
					+ mid01
					+ "><PARAM NAME=SRC VALUE="
					+ mid1
					+ "><PARAM NAME=AUTOSTART VALUE=-1><PARAM NAME=CONTROLS VALUE=controlpanel><PARAM NAME=CONSOLE VALUE=Clip1></OBJECT>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/mp]")) {
				// mideaplayer
				String mid0 = midstr.substring(head.length(), index2);
				String mid01 = "200";
				String mid02 = "200";
				int index0 = mid0.indexOf(",");
				if (index0 > 0) {
					mid01 = mid0.substring(1, index0);
					mid02 = mid0.substring(index0 + 1);
				}

				other = "<object align=middle classid=CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95 class=OBJECT id=MediaPlayer width="
					+ mid01
					+ " height="
					+ mid02
					+ " ><param name=ShowStatusBar value=-1><param name=Filename value="
					+ mid1
					+ "><embed type=application/x-oleobject codebase=http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701 flename=mp src="
					+ mid1 + " width=" + mid01 + " height=" + mid02 + "></embed></object>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/qt]")) {
				// quicktime
				String mid0 = midstr.substring(head.length(), index2);
				String mid01 = "200";
				String mid02 = "200";
				int index0 = mid0.indexOf(",");
				if (index0 > 0) {
					mid01 = mid0.substring(1, index0);
					mid02 = mid0.substring(index0 + 1);
				}
				other = "<embed src="
					+ mid1
					+ " width="
					+ mid01
					+ " height="
					+ mid02
					+ " autoplay=true loop=false controller=true playeveryframe=false cache=false scale=TOFIT bgcolor=#000000 kioskmode=false targetcache=false pluginspage=http://www.apple.com/quicktime/>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/quote]")) {
				
				other = "<table style=\"width:100%\" cellpadding=5 cellspacing=1><TR><TD width=\"100%\">"
					+ mid1 + "</td></tr></table><br>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/sound]")) {
				
				other = "<a href="
					+ mid1
					+ " target=_blank><IMG SRC=bbs_images/files/mid.gif border=0 alt='��������'></a><bgsound src="
					+ mid1 + " loop=\"-1\">";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/s520face]")) {
				
				other = "<OBJECT codeBase=http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=4,0,2,0 classid=clsid:D27CDB6E-AE6D-11cf-96B8-444553540000 width=60 height=60><PARAM NAME=movie VALUE=/UserImages/bbs_images/faceshow/"
					+ mid1
					+ ".swf><PARAM NAME=quality VALUE=high><embed src=/UserImages/bbs_images/bbs_faces/"
					+ mid1
					+ ".swf quality=high pluginspage='http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash' type='application/x-shockwave-flash' width=60 height=60></embed></OBJECT>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/glow]")) {
				
				String mid0 = midstr.substring(head.length(), index2);
				String mid01 = "100";
				String mid02 = "100";
				String mid03 = "100";
				int index0 = mid0.indexOf(",");
				if (index0 > 0) {
					mid01 = mid0.substring(1, index0);
					String mid00 = mid0.substring(index0 + 1);
					int index01 = mid00.indexOf(",");
					if (index01 > 0) {
						mid02 = mid00.substring(0, index01);
						mid03 = mid00.substring(index01 + 1);
					}
				}
				other = "<table width=" + mid01 + " ><tr><td style=\"filter:glow(color=" + mid02
					+ ", strength=" + mid03 + ")\">" + mid1 + "</td></tr></table>";
				code = leftstr + other + rightstr;
			} else if (foot.equals("[/shadow]")) {
				String mid0 = midstr.substring(head.length(), index2);
				String mid01 = "100";
				String mid02 = "100";
				String mid03 = "100";
				int index0 = mid0.indexOf(",");
				if (index0 > 0) {
					mid01 = mid0.substring(1, index0);
					String mid00 = mid0.substring(index0 + 1);
					int index01 = mid00.indexOf(",");
					if (index01 > 0) {
						mid02 = mid00.substring(0, index01);
						mid03 = mid00.substring(index01 + 1);
					}
				}
				other = "<table width=" + mid01 + " ><tr><td style=\"filter:shadow(color=" + mid02
					+ ", strength=" + mid03 + ")\">" + mid1 + "</td></tr></table>";
				code = leftstr + other + rightstr;
			} else {
				midstr = "<" + midstr.substring(1);
				midstr = midstr.substring(0, index2) + ">" + midstr.substring(index2 + 1);
				midstr = midstr.substring(0, index3 - index1) + "<"
					+ midstr.substring(index3 - index1 + 1);
				midstr = midstr.substring(0, index4 - index1) + ">";
				code = leftstr + midstr + rightstr;
			}
		}
		return code;
	}

	
	public static String replaceStr(String fromstr, String oldstr, String newstr) {
		String tostr = fromstr;
		int index = 0;
		while (index >= 0) {
			index = tostr.indexOf(oldstr, index);
			if (index < 0) {
				break;
			}
			String leftstr = tostr.substring(0, index);
			String rightstr = tostr.substring(index + oldstr.length());
			tostr = leftstr + newstr + rightstr;
		}
		return tostr;
	}

	
	public static String getImgHtml(String fileName, int width, int height) {
		String reHtml = "";
		if (isNull(fileName))
			return reHtml;
		int extL = fileName.lastIndexOf(".");
		if (extL > 0) { 
			String ext = fileName.substring(extL + 1).toLowerCase();
			if ("swf".equals(ext)) {
				reHtml = "<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\"\r";
				reHtml = reHtml
					+ "codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0\"\r";
				reHtml = reHtml + "width=\"" + width + "\" height=\"" + height + "\">\r";
				reHtml = reHtml + "<param name=\"movie\" value=\"" + fileName + "\">\r";
				reHtml = reHtml + "<param name=\"quality\" value=\"high\">\r";
				reHtml = reHtml + "<embed src=\"" + fileName + "\"\r";
				reHtml = reHtml + "quality=\"high\"\r";
				reHtml = reHtml + "pluginspage=\"http://www.macromedia.com/go/getflashplayer\"\r";
				reHtml = reHtml + "type=\"application/x-shockwave-flash\"\r";
				reHtml = reHtml + "width=\"" + width + "\" height=\"" + height + "\">\r";
				reHtml = reHtml + "</embed>\r";
				reHtml = reHtml + "</object>\r";
			} else {
				reHtml = "<img src='" + fileName + "' border=0 width='" + width + "' height='"
					+ height + "'>";
			}
		}
		return reHtml;
	}

	public static int getRandomInt(int argm) {
		return (int) (Math.random() * argm);
	}

	public static int getGradeInt(String grade) {
		if (grade == null || grade.length() != 3)
			return 0;
		String gradeIntStr = grade.substring(1);
		int g = str2Int(gradeIntStr);
		if (g > 0)
			return g;
		else
			return 0;
	}

	// ת����ҳ�����
	public static String getOutContentHtml(String content) {
		if (isNull(content)) {
			return "��������!";
		}
		content = content.replaceAll("��", "&bull;");
		content = content.replaceAll("��", "&mdash;");
		return content;
	}

	public static String str2ContentStr(String textinfo) {
		// String s = StrTools.replace(textinfo, "\"", "\\\"");
		String s = StrTools.replace(textinfo, "'", "&acute;");
		s = StrTools.replace(s, "��", "&bull;");
		s = StrTools.replace(s, "�D", "&mdash;");
		s = StrTools.replace(s, "��", "&mdash;");
		// s = StrTools.replace(s, "\n", "\\n");
		// s = StrTools.replace(s, "\r", "\\r");
		return s;
	}

	public static String maxLengthStr(String reStr, int length) {
		if (length > 0 && reStr != null && reStr.length() > length) {
			String tempstr = reStr;
			if (tempstr.length() > length) {
				tempstr = tempstr.substring(0, length - 2) + "..";
			}
			reStr = tempstr;
		}
		return reStr;
	}

	public static String GB2UTF(String strIn) {
		String strOut = null;
		String strDefault = "";
		if (strIn == null || strIn.length() == 0)
			return strDefault;
		try {
			byte[] b = strIn.getBytes("iso8859-1");
			strOut = new String(b, "GB2312");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return strOut;
	}

	public static int getMaxDayOfMonths(int year, int month) {
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date(year, month - 1, 1);
		calendar.setTime(trialTime);
		return calendar.getActualMaximum(java.util.Calendar.DATE);
	}

	public static String getCurrentDateCN() {
		// String reDate = "";
		Date date = new Date();

		DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.LONG);
		return fullDateFormat.format(date);
	}

	public static int arrayIntAdd(int[] array) {
		int arraySum = 0;
		for (int i = 0; i < array.length; i++) {
			arraySum += array[i];
		}

		return arraySum;
	}

	public static String sqlString(String search) {
		return search.replaceAll("\'", "").replaceAll(" ", "");
	}

	public static boolean isNewDate(long d) {
		long cD = getCurrentDateLong();
		if ((cD - d) <= (86400000l * 2)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getStrings2Select(String name, String[] list) {
		return getStrings2Select(name, list, 0, true);
	}

	public static String getStrings2Select(String name, String[] list, boolean isUpdate) {
		return getStrings2Select(name, list, 0, isUpdate);
	}

	public static String getStrings2Select(String name, String[] list, int value, boolean isUpdate) {
		try {

			String reStr = "<Select name=" + name + "" + (isUpdate ? "" : " disabled") + ">";
			for (int i = 0; i < list.length; i++) {
				if (i == value)
					reStr += "<option value=" + i + " selected>" + list[i] + "</option>\r";
				else
					reStr += "<option value=" + i + ">" + list[i] + "</option>\r";
			}
			reStr += "</Select>";
			return reStr;

		} catch (Exception ex) {
			return "";
		}
	}

	public static String getStrings2SelectOne(String name, String[] list, int value,
		boolean isUpdate) {
		try {

			String reStr = "<Select name=" + name + "" + (isUpdate ? "" : " disabled") + ">";
			reStr += "<option value='-1'>ȫ��</option>\r";
			for (int i = 0; i < list.length; i++) {
				if (i == value)
					reStr += "<option value=" + i + " selected>" + list[i] + "</option>\r";
				else
					reStr += "<option value=" + i + ">" + list[i] + "</option>\r";
			}
			reStr += "</Select>";
			return reStr;
		} catch (Exception ex) {
			return "";
		}
	}

	public static long getMonthFirstDay(int year, int month) {
		int yearr = year;
		String monthh = (month >= 10 ? Tools.int2Str(month) : "0" + Tools.int2Str(month));
		String _mouthFistDay = "" + yearr + "/" + monthh + "/01";
		return Tools.str2Date(_mouthFistDay).getTime();
	}

	public static long getMonthEndDay(int year, int month) {
		int yearr = year;
		String monthh = (month >= 10 ? Tools.int2Str(month) : "0" + Tools.int2Str(month));
		String _monthEndDay = "";
		if (Tools.str2Int(monthh) + 1 <= 12) {
			_monthEndDay = "" + yearr + "/" + (Tools.str2Int(monthh) + 1) + "/01";
		} else {
			_monthEndDay = "" + (yearr + 1) + "/01/01";
		}
		return Tools.str2Date(_monthEndDay).getTime() - 1000;
	}

	public static long getVnetMonthFirstDay(int year, int month) {
		int yearr = year;
		String monthh = (month >= 10 ? Tools.int2Str(month) : "0" + Tools.int2Str(month));
		String _mouthFistDay = "" + yearr + "/" + monthh + "/21";
		return Tools.str2Date(_mouthFistDay).getTime();
	}

	public static long getVnetMonthEndDay(int year, int month) {
		int yearr = year;
		String monthh = (month >= 10 ? Tools.int2Str(month) : "0" + Tools.int2Str(month));
		String _monthEndDay = "";
		if (Tools.str2Int(monthh) + 1 <= 12) {
			_monthEndDay = "" + yearr + "/" + (Tools.str2Int(monthh) + 1) + "/20";
		} else {
			_monthEndDay = "" + (yearr + 1) + "/01/20";
		}
		return Tools.str2Date(_monthEndDay).getTime() - 1000;
	}

	public static String getGradeChinese(int _grade) {
		return getNumberChinese(_grade) + "�꼶";
	}

	public static String[] NUMBER_CHINESE = { "��", "һ", "��", "��", "��", "��", "��", "��", "��",
		"��", "ʮ" };

	public static String getNumberChinese(int _grade) {
		if (_grade >= 0 && _grade <= 10) {
			return NUMBER_CHINESE[_grade];
		}
		return "" + _grade;
	}

	public static String MD5Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString.toUpperCase();
	}

	public static final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public static String get2Digit(int num) {
		return num < 10 ? "0" + num : "" + num;
	}

	public static String URLEncode(String str) {
		String reStr = str;
		try {
			reStr = URLEncoder.encode(reStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return reStr;
	}

	public static String URLDecode(String str) {
		String reStr = str;
		try {
			reStr = URLDecoder.decode(reStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return reStr;
	}

	// 取得指定字符的随机方法
	static Random r = new Random();

	// static String ssource = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	// + "abcdefghijklmnopqrstuvwxyz" + "0123456789";
	static String ssource = "ABCDEFGHIJKLMNPQRSTUVWXYZ" + "123456789";

	static char[] src = ssource.toCharArray();

	public static String randString(int length) {
		char[] buf = new char[length];
		int rnd;
		for (int i = 0; i < length; i++) {
			rnd = Math.abs(r.nextInt()) % src.length;
			buf[i] = src[rnd];
		}
		return new String(buf);
	}

	// 格式化字符
	public static String formatString(String str, int strLength, char repStr) {
		if (str.length() >= strLength)
			return str;
		StringBuilder sb = new StringBuilder();
		for (int i = 0, j = str.length(); i < strLength - j; i++) {
			sb.append(repStr);
		}
		return sb.append(str).toString();
	}
	
	public static String iso2utf8(String str) throws Exception{
		if(isNull(str)) 
			return "";
		String s = new String(str.getBytes("iso8859-1"),"utf-8");
		return s;
	}
	
	public static boolean isValidEmailAddr(String email) {
		 if( email.indexOf("@") < 1) {
			 return false;
		 }
		 if( email.indexOf("..") > -1) {
			 return false;
		 }
		 char endchar = email.charAt(email.length()-1) ;
		 if( endchar == '/' || endchar == '.') {
			 return false;
		 }

	     return true;
	}

	
	public static void main(String[] s) {
		
	}
}
