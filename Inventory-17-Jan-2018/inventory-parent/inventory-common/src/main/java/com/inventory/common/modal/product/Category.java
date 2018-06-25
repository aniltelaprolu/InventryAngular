package com.inventory.common.modal.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.inventory.common.modal.base.BaseAuditModel;

@Entity
@Table(name = "category")
public class Category extends BaseAuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1987228004059793183L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private Long categoryId;
	
	@Column(name = "name")
	private String categoryName;
	
	@Column(name = "category_code")
	private String categoryCode;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "menu_group_id",nullable=false)
	private MenuGroup menuGroup;
	
	/*@OneToMany(targetEntity=SubCategory.class,fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
	private List<SubCategory> subCategories;*/
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<SubCategory> subCategories;
	
	/**
	 * @return the menuGroup
	 */
	public MenuGroup getMenuGroup() {
		return menuGroup;
	}

	/**
	 * @param menuGroup the menuGroup to set
	 */
	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the categoryCode
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * @param categoryCode the categoryCode to set
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * @return the subCategories
	 */
	public List<SubCategory> getSubCategories() {
		return subCategories;
	}

	/**
	 * @param subCategories the subCategories to set
	 */
	public void setSubCategories(List<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}


}
