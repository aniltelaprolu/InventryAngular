package com.inventory.common.modal.offer;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.inventory.common.constants.AppConstants.OFFERSTATUS;
import com.inventory.common.modal.base.BaseAuditModel;
import com.inventory.common.modal.receive.order.InventoryItem;
import com.inventory.common.modal.receive.order.ReceivedOrderDetail;
import com.inventory.common.modal.vendor.InvVendor;

@Entity
@Table(name="item_level_offer")
public class ItemLevelOffer extends BaseAuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3894802837299504175L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="item_level_offer_id")
	private Long offerDetailId;
	
	@Column(name="name")
	private String offerName;
	
	@Column(name="quantity")
	private Integer quantity;
	
	/*@Column(name="remaining_quantity")
	private Integer remainingQuantity;*/
	
	@Column(name="offer_code")
	private Long offerCode;
	
	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column(name="description")
	private String description;
	
	@Column(name="discount_percentage")
	private Double discountPercentage;
	
	@Column(name="flat_discount")
	private Double flatDiscount;
	
	@ManyToOne(optional = true)
    @JoinColumn(name = "vender_id",nullable=true)
	private InvVendor invVendor;
	
	@Enumerated(EnumType.STRING)
	@Column(name="offer_status")
	private OFFERSTATUS offerStatus;
	
	/*@Column(name="product_name")
	private String productName;
	
	@Column(name="variant_name")
	private String variantName;*/
	
	/*@OneToMany(targetEntity=InventoryItem.class,fetch=FetchType.LAZY,cascade= {CascadeType.REMOVE,CascadeType.ALL})
	@JoinColumn(name="item_level_offer_id")
	private Set<InventoryItem> items;*/
	
	private Long productId;
	
	private Long variantId;
	
	
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

	@OneToMany(mappedBy = "itemLevelOffer", cascade= {CascadeType.REMOVE,CascadeType.ALL})
	private Set<InventoryItem> items;

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

	/**
	 * @return the invVendor
	 */
	public InvVendor getInvVendor() {
		return invVendor;
	}

	/**
	 * @param invVendor the invVendor to set
	 */
	public void setInvVendor(InvVendor invVendor) {
		this.invVendor = invVendor;
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
	 * @return the items
	 */
	public Set<InventoryItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Set<InventoryItem> items) {
		this.items = items;
	}
}
