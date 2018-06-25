/**
 * 
 */
package com.inventory.web.rest.vo.offer;

import java.util.Date;

import com.inventory.common.constants.AppConstants.OFFERSTATUS;

/**
 * @author ES002
 *
 */
public class ItemLevelOfferVO {
	private Long offerDetailId;
	private Long productId;
	private Long variantId;
	private int quantity;
	private int givenQuantity;
	private String offerName;
	private Long offerCode;
	private Date startDate;
	private Date endDate;
	private String description;
	private Double discountPercentage;
	private Double flatDiscount;
	private OFFERSTATUS offerStatus;
	
	/**
	 * @return the givenQuantity
	 */
	public int getGivenQuantity() {
		return givenQuantity;
	}
	/**
	 * @param givenQuantity the givenQuantity to set
	 */
	public void setGivenQuantity(int givenQuantity) {
		this.givenQuantity = givenQuantity;
	}
	/**
	 * @return the offerStatus
	 */
	public OFFERSTATUS getOfferStatus() {
		return offerStatus;
	}
	/**
	 * @param offerStatus the offerStatus to set
	 */
	public void setOfferStatus(OFFERSTATUS offerStatus) {
		this.offerStatus = offerStatus;
	}
	/**
	 * @return the offerDetailId
	 */
	public Long getOfferDetailId() {
		return offerDetailId;
	}
	/**
	 * @param offerDetailId the offerDetailId to set
	 */
	public void setOfferDetailId(Long offerDetailId) {
		this.offerDetailId = offerDetailId;
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
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the offerName
	 */
	public String getOfferName() {
		return offerName;
	}
	/**
	 * @param offerName the offerName to set
	 */
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	/**
	 * @return the offerCode
	 */
	public Long getOfferCode() {
		return offerCode;
	}
	/**
	 * @param offerCode the offerCode to set
	 */
	public void setOfferCode(Long offerCode) {
		this.offerCode = offerCode;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the discountPercentage
	 */
	public Double getDiscountPercentage() {
		return discountPercentage;
	}
	/**
	 * @param discountPercentage the discountPercentage to set
	 */
	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	/**
	 * @return the flatDiscount
	 */
	public Double getFlatDiscount() {
		return flatDiscount;
	}
	/**
	 * @param flatDiscount the flatDiscount to set
	 */
	public void setFlatDiscount(Double flatDiscount) {
		this.flatDiscount = flatDiscount;
	}
	
	
}
