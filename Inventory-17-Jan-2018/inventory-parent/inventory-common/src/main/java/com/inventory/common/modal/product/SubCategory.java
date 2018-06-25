package com.inventory.common.modal.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.inventory.common.modal.base.BaseAuditModel;

@Entity
@Table(name="sub_category")
public class SubCategory extends BaseAuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2244646431886282182L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sub_category_id")
	private Long subCategoryId;
	
	@Column(name = "name")
	private String subCategoryName;
	
	@Column(name = "sub_category_code")
	private String subCategoryCode;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "category_id",nullable=false)
	private Category category;

	/**
	 * @return the subCategoryId
	 */
	public Long getSubCategoryId() {
		return subCategoryId;
	}

	/**
	 * @param subCategoryId the subCategoryId to set
	 */
	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	/**
	 * @return the subCategoryCode
	 */
	public String getSubCategoryCode() {
		return subCategoryCode;
	}

	/**
	 * @param subCategoryCode the subCategoryCode to set
	 */
	public void setSubCategoryCode(String subCategoryCode) {
		this.subCategoryCode = subCategoryCode;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
