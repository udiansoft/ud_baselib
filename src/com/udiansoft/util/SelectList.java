package com.udiansoft.util;

import java.io.Serializable;
import java.util.List;

public class SelectList implements Serializable {
	private transient List<SelectOption> selectList;

	private SelectOption currentSelect;

	@SuppressWarnings("unchecked")
	public SelectList(List list) {
		selectList = list;
	}

	public SelectOption getCurrentSelect() {
		return currentSelect;
	}

	public void setCurrentSelect(SelectOption currentSelect) {
		this.currentSelect = currentSelect;
	}

	public List<SelectOption> getSelectList() {
		return selectList;
	}

	@SuppressWarnings("unchecked")
	public void setSelectList(List selectList) {
		this.selectList = selectList;
	}

}
