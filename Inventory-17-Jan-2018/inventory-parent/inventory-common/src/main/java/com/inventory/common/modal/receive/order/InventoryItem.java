package com.inventory.common.modal.receive.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.inventory.common.constants.AppConstants.POITEMSTATUS;
import com.inventory.common.modal.base.BaseAuditModel;
import com.inventory.common.modal.offer.ItemLevelOffer;
import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.Variant;
import com.inventory.common.modal.vendor.InvVendor;

@Entity
@Table(name="inventory_item")
public class InventoryItem extends BaseAuditModel{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="inventory_item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long inventoryItemId;
	
	@Column(name="inventory_item_no")
	private String inventoryItemNo;
	
	@Column(name="purchase_date")
	private Date purchaseDate;
	
	@Column(name = "purchase_price")
	private Double purchasePrice;
	
	@Column(name = "selling_price")
	private Double sellingPrice;

	@Column(name = "offer_price")
	private Double offerPrice;
	
	@Enumerated(EnumType.STRING)
	@Column(name="inventory_item_status")
	private POITEMSTATUS inventoryItemStatus;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "variant_id")
	private Variant variant;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "vender_id")
	private InvVendor invVendor;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "received_order_id")
	private ReceivedOrder receivedOrder;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "item_level_offer_id",nullable=true)
	private ItemLevelOffer itemLevelOffer;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	private Product product;
	
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the itemLevelOffer
	 */
	public ItemLevelOffer getItemLevelOffer() {
		return itemLevelOffer;
	}

	/**
	 * @param itemLevelOffer the itemLevelOffer to set
	 */
	public void setItemLevelOffer(ItemLevelOffer itemLevelOffer) {
		this.itemLevelOffer = itemLevelOffer;
	}

	/**
	 * @return the inventoryItemId
	 */
	public Long getInventoryItemId() {
		return inventoryItemId;
	}

	/**
	 * @param inventoryItemId the inventoryItemId to set
	 */
	public void setInventoryItemId(Long inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	/**
	 * @return the inventoryItemNo
	 */
	public String getInventoryItemNo() {
		return inventoryItemNo;
	}

	/**
	 * @param inventoryItemNo the inventoryItemNo to set
	 */
	public void setInventoryItemNo(String inventoryItemNo) {
		this.inventoryItemNo = inventoryItemNo;
	}

	/**
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * @return the purchasePrice
	 */
	public Double getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * @param purchasePrice the purchasePrice to set
	 */
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * @return the sellingPrice
	 */
	public Double getSellingPrice() {
		return sellingPrice;
	}

	/**
	 * @param sellingPrice the sellingPrice to set
	 */
	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	/**
	 * @return the offerPrice
	 */
	public Double getOfferPrice() {
		return offerPrice;
	}

	/**
	 * @param offerPrice the offerPrice to set
	 */
	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}

	/**
	 * @return the inventoryItemStatus
	 */
	public POITEMSTATUS getInventoryItemStatus() {
		return inventoryItemStatus;
	}

	/**
	 * @param inventoryItemStatus the inventoryItemStatus to set
	 */
	public void setInventoryItemStatus(POITEMSTATUS inventoryItemStatus) {
		this.inventoryItemStatus = inventoryItemStatus;
	}

	/**
	 * @return the variant
	 */
	public Variant getVariant() {
		return variant;
	}

	/**
	 * @param variant the variant to set
	 */
	public void setVariant(Variant variant) {
		this.variant = variant;
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
	 * @return the receivedOrder
	 */
	public ReceivedOrder getReceivedOrder() {
		return receivedOrder;
	}

	/**
	 * @param receivedOrder the receivedOrder to set
	 */
	public void setReceivedOrder(ReceivedOrder receivedOrder) {
		this.receivedOrder = receivedOrder;
	}
}