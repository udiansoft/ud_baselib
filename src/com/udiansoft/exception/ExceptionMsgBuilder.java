package com.udiansoft.exception;

public class ExceptionMsgBuilder {
	public static String buildExceptionMsg(Exception e) {
		StringBuffer sb = new StringBuffer();
		sb.append("错误消息：<br>");
		sb.append(e.getMessage());
		sb.append("<br><br>");

		StackTraceElement[] ste = e.getStackTrace();
		sb.append("错误跟踪：<br>");
		for(int i=0; i<ste.length; i++) {
			sb.append(ste[i].toString());
			sb.append("<br>");
		}

		return sb.toString();
	}

}
