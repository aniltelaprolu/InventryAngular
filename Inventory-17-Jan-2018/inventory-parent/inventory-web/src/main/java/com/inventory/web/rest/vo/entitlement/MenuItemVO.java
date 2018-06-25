package com.inventory.web.rest.vo.entitlement;

import java.util.Set;

public class MenuItemVO {
	private Integer menuItemId;
	
	private String name;
	
	private String menuItemKey;
	
	private String url;
	
	private Boolean enable;
	
	private Set<ActionVO> actionVos;

	/**
	 * @return the menuItemId
	 */
	public Integer getMenuItemId() {
		return menuItemId;
	}

	/**
	 * @param menuItemId the menuItemId to set
	 */
	public void setMenuItemId(Integer menuItemId) {
		this.menuItemId = menuItemId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the menuItemKey
	 */
	public String getMenuItemKey() {
		return menuItemKey;
	}

	/**
	 * @param menuItemKey the menuItemKey to set
	 */
	public void setMenuItemKey(String menuItemKey) {
		this.menuItemKey = menuItemKey;
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

	/**
	 * @return the enable
	 */
	public Boolean getEnable() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	/**
	 * @return the actionVos
	 */
	public Set<ActionVO> getActionVos() {
		return actionVos;
	}

	/**
	 * @param actionVos the actionVos to set
	 */
	public void setActionVos(Set<ActionVO> actionVos) {
		this.actionVos = actionVos;
	}
}
