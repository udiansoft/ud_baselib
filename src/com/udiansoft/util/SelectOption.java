package com.udiansoft.util;

import java.io.Serializable;

public class SelectOption implements Serializable {
    private String label;
    private boolean selected;
    private String  flag = "";
    private String value;
    
    public SelectOption(String value, String label) {
        setLabel(label);
        setValue(value);
    }

    public String getLabel() { return label; }

    public void setLabel(String value) {
        label = value;
    }
    
    public boolean getSelected() { return selected; }

    public void setSelected(boolean value) {
        selected = value;
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}