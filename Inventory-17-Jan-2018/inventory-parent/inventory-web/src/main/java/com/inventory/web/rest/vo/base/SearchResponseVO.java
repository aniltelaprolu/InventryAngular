package com.inventory.web.rest.vo.base;

import java.util.List;

public class SearchResponseVO<T> {

	private String messageId;
	
	private String messageDetail;
	
	private Integer currentPage;
	
	private List<T> data;

	
	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the messageDetail
	 */
	public String getMessageDetail() {
		return messageDetail;
	}

	/**
	 * @param messageDetail the messageDetail to set
	 */
	public void setMessageDetail(String messageDetail) {
		this.messageDetail = messageDetail;
	}

	/**
	 * @return the currentPage
	 */
	public Integer getcurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setcurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}
}