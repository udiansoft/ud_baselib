package com.udiansoft.saas.model;

public class RequestModel {
	private String appusername;           //此参数单独列出来，用于检查用户名是否重复
	private String dsname;                //数据源的名称
	private String agentJson;
	private String custJson;
	private String userJson;
	private String timeNoticeJson;         //此参数为应用服务器创建实例成功并应答至认证服务器后，认证服务器将开通时间及根据订单购买时长计算的到期时间通知给应用服务器

	public String getAppusername() {
		return appusername;
	}

	public void setAppusername(String appusername) {
		this.appusername = appusername;
	}

	public String getDsname() {
		return dsname;
	}

	public void setDsname(String dsname) {
		this.dsname = dsname;
	}

	public String getAgentJson() {
		return agentJson;
	}

	public void setAgentJson(String agentJson) {
		this.agentJson = agentJson;
	}

	public String getCustJson() {
		return custJson;
	}

	public void setCustJson(String custJson) {
		this.custJson = custJson;
	}

	public String getUserJson() {
		return userJson;
	}

	public void setUserJson(String userJson) {
		this.userJson = userJson;
	}

	public String getTimeNoticeJson() {
		return timeNoticeJson;
	}

	public void setTimeNoticeJson(String timeNoticeJson) {
		this.timeNoticeJson = timeNoticeJson;
	}

}
