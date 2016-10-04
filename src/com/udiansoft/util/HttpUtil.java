package com.udiansoft.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

	/**
	 * 获取客户端IP
	 * 
	 * @param request
	 * @return
	 * 注意用localhost访问时，取得的IP地址是0:0:0:0:0:0:0:1
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if(request==null)
			return "";
		
		/*java.util.Enumeration e = request.getHeaderNames();
		while(e.hasMoreElements()) {
			String hn = e.nextElement().toString();
			System.out.println("---- " + hn + " --:" + request.getHeader(hn));
		}*/
		
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取对应ip的物理网卡地址
	 * @param ip
	 * @return
	 */
	public String getMACAddress(String ip) {
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(
								str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
			input.close();
			ir.close();
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress;
	}

	public static String getRootPath(ServletContext sctx) {
		String rootpath = sctx.getRealPath("/");
		if (rootpath != null) {
			rootpath = rootpath.replaceAll("\\", "/");
		} else {
			rootpath = "/";
		}
		if (!rootpath.endsWith("/")) {
			rootpath = rootpath + "/";
		}
		return rootpath;
	}

	/**
	 * 判断客户端是pc端还是移动端
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserAgentType(HttpServletRequest request) {
		String uatype = null;
		if(request == null) return uatype;
		
		String userAgent = request.getHeader("user-agent");  
        if(userAgent != null ){  
           userAgent = userAgent.toUpperCase();  
		   /*if (userAgent.indexOf("NOKI") > -1 || // Nokia phones and emulators
					userAgent.indexOf("ERIC") > -1 || // Ericsson WAP phones and emulators
					userAgent.indexOf("WAPI") > -1 || // Ericsson WapIDE 2.0
					userAgent.indexOf("MC21") > -1 || // Ericsson MC218
					userAgent.indexOf("AUR") > -1 || // Ericsson R320
					userAgent.indexOf("R380") > -1 || // Ericsson R380
					userAgent.indexOf("UP.B") > -1 || // UP.Browser
					userAgent.indexOf("WINW") > -1 || // WinWAP browser
					userAgent.indexOf("UPG1") > -1 || // UP.SDK 4.0
					userAgent.indexOf("UPSI") > -1 || // another kind of UP.Browser
					userAgent.indexOf("QWAP") > -1 || // unknown QWAPPER browser
					userAgent.indexOf("JIGS") > -1 || // unknown JigSaw browser
					userAgent.indexOf("JAVA") > -1 || // unknown Java based browser
					userAgent.indexOf("ALCA") > -1 || // unknown Alcatel-BE3 browser (UP based)
					userAgent.indexOf("MITS") > -1 || // unknown Mitsubishi browser
					userAgent.indexOf("MOT-") > -1 || // unknown browser (UP based)
					userAgent.indexOf("MY S") > -1 || // unknown Ericsson devkit browser
					userAgent.indexOf("WAPJ") > -1 || // Virtual WAPJAG
					userAgent.indexOf("FETC") > -1 || // fetchpage.cgi Perl script from www.wapcab.de
					userAgent.indexOf("ALAV") > -1 || // yet another unknown UP based browser
					userAgent.indexOf("WAPA") > -1 || // another unknown browser (Web based "Wapalyzer")
					userAgent.indexOf("OPER") > -1 || // Opera
					userAgent.indexOf("DOPOD") > -1 || // 多普达
					userAgent.indexOf("SYMBIAN") > -1 // symbian系统
			) { */
		   if (userAgent.indexOf("ANDROID") > -1 || // Nokia phones and emulators
					userAgent.indexOf("MOBILE") > -1 || // Ericsson WAP phones and emulators
					userAgent.indexOf("IPHONE") > -1 || // Ericsson WAP phones and emulators
					userAgent.indexOf("IPAD") > -1 //|| // Ericsson WAP phones and emulators
					//userAgent.indexOf("SAFARI") > -1    // Ericsson WapIDE 2.0
			) {
			    uatype = "MOBILE";       // 通过手机访问
			} else {
				uatype = "PC";       // PC
			}
        }  

        return uatype;
	}
}