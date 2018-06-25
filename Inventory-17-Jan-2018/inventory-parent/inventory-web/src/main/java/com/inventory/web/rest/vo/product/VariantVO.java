package com.inventory.web.rest.vo.product;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class VariantVO {
	
	private Long vendorId;
	
	private Long variantId;
	
	private long productId;
	
	private String productName;
	
	private String vendorName;
	
	private String variantName;
	
	private Double price;
	
	private Integer quantity;
	
	//List<ItemsVO> items;
	
	List<VariantParameterVO> variantParams;
	
	private String imagePath;
	
	private List<MultipartFile> multipartFiles;

	/**
	 * @return the variantParams
	 */
	public List<VariantParameterVO> getVariantParams() {
		return variantParams;
	}

	/**
	 * @param variantParams the variantParams to set
	 */
	public void setVariantParams(List<VariantParameterVO> variantParams) {
		this.variantParams = variantParams;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public Long getVariantId() {
		return variantId;
	}

	public void setVariantId(Long variantId) {
		this.variantId = variantId;
	}

	public String getVariantName() {
		return variantName;
	}

	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	
	
}
