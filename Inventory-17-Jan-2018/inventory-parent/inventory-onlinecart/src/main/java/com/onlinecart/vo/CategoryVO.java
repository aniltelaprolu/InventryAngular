package com.onlinecart.vo;

import java.util.List;

public class CategoryVO {
	
	private Long menuGroupId;
	
	private Long categoryId;
	
	private String categoryName;
	
	private String categoryCode;
	
	private List<SubCategoryVO> subCategory;
	
	public Long getMenuGroupId() {
		return menuGroupId;
	}
	public void setMenuGroupId(Long menuGroupId) {
		this.menuGroupId = menuGroupId;
	}
	public List<SubCategoryVO> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(List<SubCategoryVO> subCategory) {
		this.subCategory = subCategory;
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
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CategoryVO [menuGroupId=" + menuGroupId + ", categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", categoryCode=" + categoryCode + ", subCategory=" + subCategory + "]";
	}
	
}
