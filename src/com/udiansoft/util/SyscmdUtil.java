package com.udiansoft.util;

import java.io.*;
import java.util.*;
import java.net.*;
import java.nio.charset.Charset;  

public class SyscmdUtil {

	@SuppressWarnings("unchecked")
	public Vector execute(String shellCommand) {

		try {

			Start(shellCommand);
			Vector vResult = new Vector();
			DataInputStream in = new DataInputStream(p.getInputStream());
			BufferedReader reader = new BufferedReader(new
			InputStreamReader(in));
			String line;
			do {
				line = reader.readLine();
				if (line == null) {
					break;
				}
				else {
					vResult.addElement(line);
				}
			} while (true);
			reader.close();
			return vResult;
		} catch (Exception e) {
			// error
			return null;
		}
	}

	/**
	 * 
	 * @param shellCommand
	 * 
	 * 
	 * 
	 */
	public void Start(String shellCommand) {
		try {
			if (p != null) {
				kill();
			}
			Runtime sys = Runtime.getRuntime();
			p = sys.exec(shellCommand);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * 
	 * kill this process
	 * 
	 */

	public void kill() {
		if (p != null) {
			p.destroy();
			p = null;
		}
	}
	Process p;
	
	private static final int _physicalLength = 17;
	//返回网卡物理地址
	public static String getPhysicalAddress() throws UnknownHostException {
//		return InetAddress.getLocalHost().getHostAddress(); // 返回IP地址
		SyscmdUtil shell = new SyscmdUtil();
		
		if( System.getenv("PATH").indexOf("\\")>0){//windows
			String cmd = "cmd.exe /c ipconfig/all";
			Vector result;
			result = shell.execute(cmd);
			String s = result.toString();
			String find = "Physical Address. . . . . . . . . :";
			int findIndex = s.indexOf(find);
			if (findIndex == -1) {
				find = "物理地址. . . . . . . . . . . . . :";
				findIndex = s.indexOf(find);
				if (findIndex == -1) {
					//中文和英文的物理地址前缀都未找到时，可能存在中文乱码问题，此时用不含中文的字符判断
					find = ". . . . . . . :";
					boolean flag = false;
					while(s.length() > find.length()) {
						findIndex = s.indexOf(find);
						if(findIndex == -1) {
							break;
						} else {
							s = s.substring(findIndex + find.length());
							//判断截取后的字串是否符合网卡物理地址的格式
							if(s.charAt(3) == '-' && s.charAt(6) == '-' && s.charAt(9) == '-' && s.charAt(12) == '-' && s.charAt(15) == '-') {
								flag = true;
								break;
							}
						}
					}
					if(flag) {
						return s.substring(1, 1 + _physicalLength);
					} else {
						return "not find";
					}
				} else {
					return s.substring(findIndex + find.length() + 1, findIndex	+ find.length() + 1 + _physicalLength);
				}
			} else {
				return s.substring(findIndex + find.length() + 1, findIndex	+ find.length() + 1 + _physicalLength);
			}
		}else{//linux
			String cmd = "ifconfig";
			Vector result;
			result = shell.execute(cmd);
			String s=result.toString();
			String find = "HWaddr";
			int findIndex = s.indexOf(find);
			if (findIndex == -1)
				return "not find";
			else
				return s.substring(findIndex + find.length() + 1, findIndex
						+ find.length() + 1 + _physicalLength);
		}
	}
	
	//执行数据备份
	public static String exeDataBackup(String binPath,String host,String port,String db,String dbuser,String dbpswd,String todir) throws UnknownHostException {
		String ret = null;
		if(binPath == null || "".equals(binPath.trim()) || db == null || "".equals(db.trim()) 
				|| dbuser == null || "".equals(dbuser.trim()) || dbpswd == null || "".equals(dbpswd.trim())) {
			ret = "参数缺失！";
			return ret;
		}
		SyscmdUtil shell = new SyscmdUtil();
		String path = getBackupDir(todir);
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String postfix = "_" + CurrentTime.getCompactDateString() + ".sql"; 
		StringBuffer cmdSb = new StringBuffer();
		cmdSb.append("cmd /c ");
		cmdSb.append(binPath);
		cmdSb.append("/mysqldump.exe");
		if(host != null && !"".equals(host.trim())) {
			cmdSb.append(" -h");
			cmdSb.append(host);
		}
		if(port != null && !"".equals(port.trim())) {
			cmdSb.append(" -P");
			cmdSb.append(port);
		}
		cmdSb.append(" -u");
		cmdSb.append(dbuser);
		cmdSb.append(" -p");
		cmdSb.append(dbpswd);
		cmdSb.append(" ");
		cmdSb.append(db);
		cmdSb.append(">");
		cmdSb.append(path);
		cmdSb.append(db);
		cmdSb.append(postfix);
		Vector result = shell.execute(cmdSb.toString());
		ret = result.toString();

		return ret;
	}
	
	public static String getBackupDir(String todir) {
		String path = null;
		if(todir != null && !"".equals(todir.trim())) {
			path = todir;
		} else {
			if(System.getenv("PATH").indexOf("\\")>0){   //windows
				path = "C:/ud_back/db/";
			} else {                                      //linux
				path = "/ud_back/db/";
			}
		}
        return path;
	}

	public static void main(String[] args) throws UnknownHostException {
		// output you computer phycail ip address
		//System.out.println(getPhysicalAddress());
//		System.out.println(StrTools.getMd5(getPhysicalAddress()));
		
		String binpath = "D:/develop/MySQL/\"MySQL Server 5.0\"/bin";
		String host = "localhost";
		String port = "3306";
		String ret = exeDataBackup(binpath,host,port,"udbpm","root","steven","D:/ud_back/db/");
		System.out.println(ret);
		ret = exeDataBackup(binpath,host,port,"cscrm","root","steven","D:/ud_back/db/");
		System.out.println(ret);
	}
}
