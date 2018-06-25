package com.inventory.web.rest.vo.entitlement;

public class ActionVO {
	private Integer actionId;
	
	private String actionKey;
	
	private Boolean enableFlag;

	private String action;
	
	private String url;

	/**
	 * @return the actionId
	 */
	public Integer getActionId() {
		return actionId;
	}

	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	/**
	 * @return the actionKey
	 */
	public String getActionKey() {
		return actionKey;
	}

	/**
	 * @param actionKey the actionKey to set
	 */
	public void setActionKey(String actionKey) {
		this.actionKey = actionKey;
	}

	/**
	 * @return the enableFlag
	 */
	public Boolean getEnableFlag() {
		return enableFlag;
	}

	/**
	 * @param enableFlag the enableFlag to set
	 */
	public void setEnableFlag(Boolean enableFlag) {
		this.enableFlag = enableFlag;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
