package com.udiansoft.util;

import java.io.*;
import java.util.*;
import java.net.*;

public class Util_syscmd {

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
	
	static private final int _physicalLength = 17;
	//返回网卡物理地址
	static public String getPhysicalAddress() throws UnknownHostException {
//		return InetAddress.getLocalHost().getHostAddress(); // 返回IP地址
		Util_syscmd shell = new Util_syscmd();
		
		if( System.getenv("PATH").indexOf("\\")>0){//windows
			String cmd = "cmd.exe /c ipconfig/all";
			Vector result;
			result = shell.execute(cmd);
			String s=result.toString();
			String find = "Physical Address. . . . . . . . . :";
			int findIndex = s.indexOf(find);
			if (findIndex == -1)
				return "not find";
			else
				return s.substring(findIndex + find.length() + 1, findIndex
						+ find.length() + 1 + _physicalLength);
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

	public static void main(String[] args) throws UnknownHostException {
		// output you computer phycail ip address
		System.out.println(getPhysicalAddress());
//		System.out.println(StrTools.getMd5(getPhysicalAddress()));
	}
}
