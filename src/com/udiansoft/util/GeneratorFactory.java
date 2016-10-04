package com.udiansoft.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GeneratorFactory {
	
	/**
	 * 静态页面生成
	 * @param templateFilePath 模板路径
	 * @param templateFileName 模板名称
	 * @param htmlFilePath     页面位置
	 * @param htmlFileName     页面名称
	 * @param propMap          页面模型
	 * @return
	 */
	public boolean generatorHTML(String templateFilePath, String templateFileName, 
			String htmlFilePath, String htmlFileName, Map propMap) {
		try {
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			//configuration.setOutputEncoding("UTF-8");
			configuration.setDirectoryForTemplateLoading(new File(templateFilePath));
			Template template = configuration.getTemplate(templateFileName);
	
			// 如果根路径存在,则递归创建子目录
			File temp = new File(htmlFilePath);
	
			if (!temp.exists()) {
				return temp.mkdirs();
			} 
	
			File file = new File(htmlFilePath + "/" + htmlFileName);
	
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8"));
			template.setEncoding("UTF-8");
			template.process(propMap, out);
			out.flush();
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		return true;
	}
}
