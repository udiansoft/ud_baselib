package com.udiansoft.saas.model;

public class InitTimeNoticeModel {
	private Integer customer_id ;
	private String begindate ;
	private String expiredate ;
	
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customerId) {
		customer_id = customerId;
	}
	public String getBegindate() {
		return begindate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	public String getExpiredate() {
		return expiredate;
	}
	public void setExpiredate(String expiredate) {
		this.expiredate = expiredate;
	}

}
