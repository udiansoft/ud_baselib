package com.udiansoft.po;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;

import com.udiansoft.util.DateTool;
import com.udiansoft.util.Tools;

public abstract class Entity {
	public abstract Field[] getEntityFields();
	
	public Object doGetMethod(Object source,String fieldname) {
		try {
				String Fldname = fieldname.substring(0,1).toUpperCase() + fieldname.substring(1);
				Method getMtd = source.getClass().getMethod("get" + Fldname);
				Object value = getMtd.invoke(source);   
				return value;
		} catch(Exception e) {
			 e.printStackTrace();
		}
		return null;
	}
	
	public void copyVal(Object source) {
		Field[] fields = getEntityFields();

		try {
		    for(int i=0; i<fields.length; i++) {
				Object value = doGetMethod(source,fields[i].getName());
				if(value != null) {
					Class[] argsType = new Class[1];
					argsType[0] = value.getClass();
					String Fldname = fields[i].getName().substring(0,1).toUpperCase() + fields[i].getName().substring(1);
					Method setMtd = this.getClass().getMethod("set" + Fldname, argsType);
					//Object[] args = new Object[1];
					//args[0] = value;
					setMtd.invoke(this, value);
				}
			 }
		} catch(Exception e) {
			 e.printStackTrace();
		}
	}
	
	public String props2Xml() {
		 Field[] fields = getEntityFields();
		 
		 StringBuffer sb = new StringBuffer();
		 try {
			 for(int i=0; i<fields.length; i++) {
				String fldName = fields[i].getName();
				Method mtd = this.getClass().getMethod("get" + fldName.substring(0,1).toUpperCase() + fldName.substring(1));
				Object value = mtd.invoke(this);    
                //需要格式处理的字段
				if(value != null && fields[i].getType().getName().indexOf("Date") > -1) {
					//value = new Date(value.toString());
	        		value = DateTool.formatDate("yyyy-MM-dd",(Date)value);
	        	}
				
				//输出为xml
		        if(value != null) {
				    sb.append("<" + fldName + ">" + value + "</" + fldName + ">");
				} else {
				    sb.append("<" + fldName + "></" + fldName + ">");
				}
			 }
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		
		 return sb.toString();
	}
	
	public String props2Map(Map map) {
		 Field[] fields = getEntityFields();
		 
		 StringBuffer sb = new StringBuffer();
		 try {
			 for(int i=0; i<fields.length; i++) {
				String fldName = fields[i].getName();
				Method mtd = this.getClass().getMethod("get" + fldName.substring(0,1).toUpperCase() + fldName.substring(1));
				Object value = mtd.invoke(this);    
               //需要格式处理的字段
				if(value != null && fields[i].getType().getName().indexOf("Date") > -1) {
					//value = new Date(value.toString());
	        		value = DateTool.formatDate("yyyy-MM-dd",(Date)value);
	        	}
				
				//将属性值PUT到map
		        if(value != null) {
				    sb.append("<" + fldName + ">" + value + "</" + fldName + ">");
				    map.put(fldName, value);
				}
			 }
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		
		 return sb.toString();
	}
	
	public void putFilterInMap(HttpServletRequest request,Map map) {
		Field[] fields = getEntityFields();
		try {
			for(int i=0; i<fields.length; i++) {
				String paramName = fields[i].getName();
				String paramVal = request.getParameter(paramName);
				if(paramVal != null && !"".equals(paramVal)) {
			       map.put(paramName, paramVal);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void putFilterInMapWithIso2utf(HttpServletRequest request,Map map) {
		Field[] fields = getEntityFields();
		try {
			for(int i=0; i<fields.length; i++) {
				String paramName = fields[i].getName();
				String paramVal = request.getParameter(paramName);
				paramVal = Tools.iso2utf8(paramVal);
				if(paramVal != null && !"".equals(paramVal)) {
			       map.put(paramName, paramVal);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public JSONObject pojo2Json() {
		 JSONObject jsobj = new JSONObject();
		 Field[] fields = getEntityFields();
		 try {
			 for(int i=0; i<fields.length; i++) {
				String fldName = fields[i].getName();
				Method mtd = this.getClass().getMethod("get" + fldName.substring(0,1).toUpperCase() + fldName.substring(1));
				Object value = mtd.invoke(this);    
                //需要格式处理的字段
				if(value != null && fields[i].getType().getName().indexOf("Date") > -1) {
	        		value = DateTool.formatDate("yyyy-MM-dd",(Date)value);
	        	}
				
				//将属性值PUT到map
		        if(value != null) {
		        	jsobj.put(fldName, value);
				}
			 }
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		
		 return jsobj;
	}
}
