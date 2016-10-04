package com.udiansoft.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;


public class FileTools {
	public FileTools() {
	}
	
	public static Properties load(String fname) {
		Properties prop = System.getProperties();
		try {
			InputStream fin = FileTools.class.getClassLoader()
					.getResourceAsStream(fname);
			prop.load(fin);
			fin.close();
			return prop;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	static public boolean saveFile(String fileName, String fileInfo) {
		try {
			File f = new File(fileName);
			createDir(f.getParent());
			FileOutputStream fout = new FileOutputStream(f);
			PrintWriter pout = new PrintWriter(fout, true);
			pout.println(fileInfo);
			fout.close();
			pout.close();
		} catch (Exception ex) {
			System.out.println("FileTools Error in saveFile fileName=" + fileName + " ... "
				+ ex.getMessage());
			return false;
		}
		return true;
	}

	static public long saveFile(String fileName, File f) {
		long fsize = 0;
		try {
			File newFile = new File(fileName);
			if(newFile.exists()) {
				newFile.delete();
			}
			createDir(newFile.getParent());
			//原来使用的是renameTo方法进行文件拷贝,但该方法有陷井,特别在LINUX下可能失效,现改用common-io的copyFile方法
			//boolean ret = f.renameTo(newFile);
			//System.out.println("----------- after f.renameTo(newFile) ...result:" + ret);
			FileUtils.copyFile(f, newFile);
			
			fsize = newFile.length();
		} catch (Exception ex) {
			System.out.println("FileTools Error in saveFile with f fileName=" + fileName + " ... "
				+ ex.getMessage());
			return -1;
		}
		return fsize;
	}

	static public boolean saveFileByte(String fileName, byte[] fileInfo) {
		try {
			File f = new File(fileName);
			createDir(f.getParent());
			FileOutputStream output = new FileOutputStream(f);
			try {
				output.write(fileInfo);
			} finally {
				output.close();
			}
		} catch (Exception ex) {
			System.out.println("FileTools Error in saveFile fileName=" + fileName + " ... "
				+ ex.getMessage());
			return false;
		}
		return true;
	}

	static public boolean saveFileString(String fileName, String fileInfo) {
		try {
			File f = new File(fileName);
			createDir(f.getParent());
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			try {
				output.write(fileInfo);
			} finally {
				output.close();
			}
		} catch (Exception ex) {
			System.out.println("FileTools Error in saveFile fileName=" + fileName + " ... "
				+ ex.getMessage());
			return false;
		}
		return true;
	}

	static public boolean createDir(String dirName) {
		try {
			File f = new File(dirName);
			if (!f.exists()) {
				f.mkdirs();
			}
		} catch (Exception ex) {
			System.out.println("FileTools Error in saveFile dirName=" + dirName + " ... "
				+ ex.getMessage());
			return false;
		}

		return true;
	}
	
	//下载图片到本地保存
    static public void download(String urlString, String filename, String savePath) throws Exception {  
    	// 构造URL  
    	URL url = new URL(urlString);  
    	// 打开连接  
    	URLConnection con = url.openConnection();  
    	//设置请求超时为5s  
    	con.setConnectTimeout(5*1000);  
    	// 输入流  
    	InputStream is = con.getInputStream();  
    	
    	// 1K的数据缓冲  
    	byte[] bs = new byte[1024];  
    	// 读取到的数据长度  
    	int len;  
    	// 输出的文件流  
    	File sf=new File(savePath);  
    	if(!sf.exists()){  
    	    sf.mkdirs();  
    	}  
    	OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);  
    	// 开始读取  
    	while ((len = is.read(bs)) != -1) {  
    	    os.write(bs, 0, len);  
    	}  
    	// 完毕，关闭所有链接  
    	os.close();  
    	is.close();  
    }   
    
    //下载图片并保存到服务器上
    static public void download2Svr(String urlString, String filename, String savePath) throws IOException,FileNotFoundException {
    	HttpClient client = new HttpClient();
    	GetMethod get = new GetMethod(urlString);

    	// 输出的文件夹  
    	File sf = new File(savePath);  
    	if(!sf.exists()){  
    	    sf.mkdirs();  
    	}
     	File storeFile = new File(savePath + filename);
   	    FileOutputStream output = null;
    	try {
    	     client.executeMethod(get);
    	     output = new FileOutputStream(storeFile);
    	     output.write(get.getResponseBody());
    	     output.close();
    	 } catch (HttpException e) {
    	     e.printStackTrace();
    	 }
    }

}
