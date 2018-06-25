package com.inventory.web.rest.vo.product;

import java.util.Set;

import com.inventory.common.constants.AppConstants.PRODUCTSTATUS;

public class ProductVO {
	private Long newVendorId;
	
	private Long vendorId;
	
	private Long productId;
	
	private String vendorName;
	
	private String productCode;
	
	private String productName;
	
	private Long menuGroupId;
	
	private Long newMenuGroupId;
	
	private long categoryId;
	
	private long newCategoryId;
	
	private Long subCategoryId;
	
	private Long newSubCategoryId;
	
	private String description;
	
	private PRODUCTSTATUS status;
	
	Set<VariantVO> variants;
	
	
	public Long getNewMenuGroupId() {
		return newMenuGroupId;
	}

	public void setNewMenuGroupId(Long newMenuGroupId) {
		this.newMenuGroupId = newMenuGroupId;
	}

	public long getNewCategoryId() {
		return newCategoryId;
	}

	public void setNewCategoryId(long newCategoryId) {
		this.newCategoryId = newCategoryId;
	}

	public Long getNewSubCategoryId() {
		return newSubCategoryId;
	}

	public void setNewSubCategoryId(Long newSubCategoryId) {
		this.newSubCategoryId = newSubCategoryId;
	}

	public Long getNewVendorId() {
		return newVendorId;
	}

	public void setNewVendorId(Long newVendorId) {
		this.newVendorId = newVendorId;
	}

	public Long getMenuGroupId() {
		return menuGroupId;
	}

	public void setMenuGroupId(Long menuGroupId) {
		this.menuGroupId = menuGroupId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PRODUCTSTATUS getStatus() {
		return status;
	}

	public void setStatus(PRODUCTSTATUS status) {
		this.status = status;
	}

	public Set<VariantVO> getVariants() {
		return variants;
	}

	public void setVariants(Set<VariantVO> variants) {
		this.variants = variants;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	
}
