package com.udiansoft.saas;

public class Const {
	public final static Integer RETCODE_SUCC = 0;                  //成功
	
	public final static Integer RETCODE_PARAM_ERR = 1001;             //参数错误
	public final static Integer RETCODE_NOBINDAPPINST = 1011;           //尚未分配应用实例
	public final static Integer RETCODE_NOFREEAPPINST = 1012;       //没有可用的应用实例
	public final static Integer RETCODE_ERR_STATUSCODE = 1099;       //错误的响应码

	public final static Integer RETCODE_USERNAME_DUP = 2001;           //用户名重复

	public final static Integer RETCODE_CONN_EXCEPTION = 3001;           //服务器连接异常
}
