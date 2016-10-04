/**
 * 
 */
package com.udiansoft.util;

/**
 * Filters and sorts lists of elements
 * 
 * @author Steven Li Create Data: Oct 13, 2006
 */
public class ResultFilter {

//	private static final int DESCENDING = 0;

//	private static final int ASCENDING = 1;

	public String getSortPropertyName() {
		return sortPropertyName;
	}

	public int getPagesize() {
		return pagesize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * Creates a default thread ResultFilter: no filtering with results sorted
	 * on the thread modification date.
	 */
	public static ResultFilter createDefaultFilter() {
		ResultFilter resultFilter = new ResultFilter();
		return resultFilter;
	}

	public ResultFilter() {

	}

//	private int sortOrder = DESCENDING;

	private String sortPropertyName = null;

	/**
	 * The starting index for results. Default is 0.
	 */
	private int startIndex = 0;

	private int pagesize = 10;

	public ResultFilter(int startIndex, int pagesize) {
		super();
		this.startIndex = startIndex;
		this.pagesize = pagesize;
	}
	
	
	public ResultFilter(int startIndex, int pagesize,String sortPorpertyName) {
		super();
		this.startIndex = startIndex;
		this.pagesize = pagesize;
		this.sortPropertyName = sortPorpertyName;
	}

}
