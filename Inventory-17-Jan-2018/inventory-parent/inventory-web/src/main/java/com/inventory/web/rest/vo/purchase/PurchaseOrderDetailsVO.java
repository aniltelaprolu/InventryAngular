/**
 * 
 */
package com.inventory.web.rest.vo.purchase;

import java.util.Date;

/**
 * @author ES003
 *
 */
public class PurchaseOrderDetailsVO {
	
	
	private Long productId;
	
	private Long variantId;
	
	private String productName;
	
	private String variantName;
	
	private Integer quantity;
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getVariantName() {
		return variantName;
	}

	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the variantId
	 */
	public Long getVariantId() {
		return variantId;
	}

	/**
	 * @param variantId the variantId to set
	 */
	public void setVariantId(Long variantId) {
		this.variantId = variantId;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
