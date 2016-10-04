package com.udiansoft.saas.model;

import net.sf.json.JSONObject;

public class ResponseModel {
	private Integer retcode;
	private String retmsg;
	
	//必须提供以下无参数的构造方法，否则以下调用行会报错：
	//(ResponseModel)JSONObject.toBean(resObj, ResponseModel.class)
	//错误信息为：java.lang.NoSuchMethodException: com.udiansoft.saas.model.ResponseModel.<init>()
	public ResponseModel() {
		this.retcode = null;
		this.retmsg = null;
	}
	
	public ResponseModel(Integer code,String msg) {
		this.retcode = code;
		this.retmsg = msg;
	}
	
	public Integer getRetcode() {
		return retcode;
	}
	
	public void setRetcode(Integer retcode) {
		this.retcode = retcode;
	}
	
	public String getRetmsg() {
		return retmsg;
	}
	
	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

	public static String getResJsonStr(Integer code,String msg) {
		ResponseModel resModel = new ResponseModel(code,msg);
		JSONObject resobj = JSONObject.fromObject(resModel);
		return resobj.toString();
	}
}
