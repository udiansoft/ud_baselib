package com.udiansoft.util;

public class UrlModel {
	public UrlModel(){
		
	}
	public UrlModel(String link, String info, boolean isOwnLink,int index) {
		this.link = link;
		this.info = info;
		this.isOwnLink = isOwnLink;
		this.index=index;
	}
	
	private String link="";
	private String info="";
	private boolean isOwnLink=false;
	private int index=0;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public boolean isOwnLink() {
		return isOwnLink;
	}
	public void setOwnLink(boolean isOwnLink) {
		this.isOwnLink = isOwnLink;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
