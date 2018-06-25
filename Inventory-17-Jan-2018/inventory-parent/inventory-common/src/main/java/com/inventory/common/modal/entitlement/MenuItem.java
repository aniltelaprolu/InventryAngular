package com.inventory.common.modal.entitlement;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "menu_item")
public class MenuItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2437855698779820179L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="menu_item_id")
	private Integer menuItemId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="menu_item_key")
	private String menuItemKey;
	
	@Column(name="url")
	private String url;
	
	@Column(name = "enable")
	private Boolean enable;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "menuItem", fetch = FetchType.LAZY)
	private Set<Action> actions;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;

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
	 * @return the actions
	 */
	public Set<Action> getActions() {
		return actions;
	}

	/**
	 * @param actions the actions to set
	 */
	public void setActions(Set<Action> actions) {
		this.actions = actions;
	}

	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
