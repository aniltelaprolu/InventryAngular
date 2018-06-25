/**
 * 
 */
package com.inventory.web.rest.controller.search;

import java.util.Map;

/**
 * @author ES002
 *
 */
public class PageResultVO {
	
	private String moduleName;
	private int pageNo;
	private int pageSize;
	private Map<String,String> columnValue;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Map<String, String> getColumnValue() {
		return columnValue;
	}
	public void setColumnValue(Map<String, String> columnValue) {
		this.columnValue = columnValue;
	}
	
	
	
}
