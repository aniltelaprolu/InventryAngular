package com.inventory.web.rest.vo.base;

public class SearchRequestVO {

	private Integer pageNo;
	
	private Integer pageSize;

	private String[] searchParams;
	
	private String[] searchParamValues;

	/**
	 * @return the pageNo
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String[] getSearchParams() {
		return searchParams;
	}

	public void setSearchParams(String[] searchParams) {
		this.searchParams = searchParams;
	}

	public String[] getSearchParamValues() {
		return searchParamValues;
	}

	public void setSearchParamValues(String[] searchParamValues) {
		this.searchParamValues = searchParamValues;
	}
}
