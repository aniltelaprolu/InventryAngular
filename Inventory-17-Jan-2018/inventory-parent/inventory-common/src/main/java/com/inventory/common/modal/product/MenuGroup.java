package com.inventory.common.modal.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.inventory.common.modal.base.BaseAuditModel;

@Entity
@Table(name = "menu_group")
public class MenuGroup extends BaseAuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "menu_group_id")
	private Long menuGroupId;

	@Column(name = "menu_group_name", unique = true)
	private String menuGroupName;

	@Column(name = "menu_group_code")
	private String menuGroupCode;

	/*
	 * @OneToMany(targetEntity=Category.class,fetch=FetchType.LAZY,cascade=
	 * CascadeType.ALL) private List<Category> categories;
	 */

	@OneToMany(mappedBy = "menuGroup", cascade = CascadeType.ALL)
	private List<Category> categories;

	/**
	 * @return the menuGroupName
	 */
	public String getMenuGroupName() {
		return menuGroupName;
	}

	/**
	 * @return the menuGroupId
	 */

	public Long getMenuGroupId() {
		return menuGroupId;
	}

	public void setMenuGroupId(Long menuGroupId) {
		this.menuGroupId = menuGroupId;
	}

	/**
	 * @param menuGroupName
	 *            the menuGroupName to set
	 */
	public void setMenuGroupName(String menuGroupName) {
		this.menuGroupName = menuGroupName;
	}

	/**
	 * @return the menuGroupCode
	 */
	public String getMenuGroupCode() {
		return menuGroupCode;
	}

	/**
	 * @param menuGroupCode
	 *            the menuGroupCode to set
	 */
	public void setMenuGroupCode(String menuGroupCode) {
		this.menuGroupCode = menuGroupCode;
	}

	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
