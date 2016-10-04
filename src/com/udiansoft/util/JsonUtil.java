package com.udiansoft.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	/** 
	   * 从一个JSON字符串得到一个Map对象
	   */  
	public static Map getMapFromJsonStr(String jsonstr) {
		JSONObject jsonObj = JSONObject.fromObject(jsonstr);
		if(jsonObj == null) {
			return null;
		}
		
		return (Map)jsonObj;
	}
	
	/** 
	   * 从一个JSON数组得到一个java对象数组，形如： 
	   * [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...] 
	   * @param object 
	   * @param clazz 
	   * @return 
	   */  
	public static Object[] getDTOArray(String jsonString, Class clazz) {
	    JSONArray array = JSONArray.fromObject(jsonString);  
	    Object[] obj = new Object[array.size()];  
	    for(int i = 0; i < array.size(); i++){  
	       JSONObject jsonObject = array.getJSONObject(i);  
	       obj[i] = JSONObject.toBean(jsonObject, clazz);  
	    }  
	    return obj;  
	}

	/** 
	   * 从一个JSON数组得到一个java对象List，形如： 
	   * [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...] 
	   * @param object 
	   * @param clazz 
	   * @return 
	   */  
	public static List getDTOList(String jsonString, Class clazz) {
	    JSONArray array = JSONArray.fromObject(jsonString);  
	    List list = new ArrayList();  
	    for(int i = 0; i < array.size(); i++){  
	       JSONObject jsonObject = array.getJSONObject(i);  
	       list.add(JSONObject.toBean(jsonObject, clazz));  
	    }  
	    return list;  
	}
	

}
