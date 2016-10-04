package com.udiansoft.util;

import java.io.Serializable;


public class TpSelectOption implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strvalue;
	private String displayName;
	
	public TpSelectOption(String value, String displayName) {
		this.strvalue = value;
		this.displayName = displayName;
	}
	
	public TpSelectOption(long id, String displayName) {
		this.strvalue = (new Long(id)).toString();
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getStrvalue() {
		return strvalue;
	}

	public void setStrvalue(String value) {
		this.strvalue = value;
	}
	
	
//	
	@Override
	public boolean equals(Object e) {
		
        if (this == e) return true;
        
        if (e == null) { 
            return false; 
    }
        if (!(e instanceof TpSelectOption))  return false; 

        /*
         * 对于其它情形，比较其strvalue
         */
        return this.strvalue.equals(((TpSelectOption)e).strvalue);
	}
	
	//
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		int result=17;
		result=37*result+(int)(strvalue.hashCode()+ displayName.hashCode());
		return result;
	}
}
