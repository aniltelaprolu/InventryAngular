package com.inventory.common.modal.product;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.inventory.common.constants.AppConstants.PRODUCTSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.base.BaseAuditModel;
import com.inventory.common.modal.receive.order.InventoryItem;
import com.inventory.common.modal.receive.order.ReceivedOrderDetail;
import com.inventory.common.modal.vendor.InvVendor;

@Entity
@Table(name="product")
public class Product extends BaseAuditModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_id")
	private Long productId;
	
	@Column(name="product_name")
	private String productName;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_group_id")
    private MenuGroup menuGroup;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;
	
	@Column(name="product_code")
	private String productCode;
	
	@Column(name="description")
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private STATUS status;
	
	@Enumerated(EnumType.STRING)
	@Column(name="product_status")
	private PRODUCTSTATUS productStatus;
	
	@OneToMany(targetEntity=Variant.class,fetch=FetchType.LAZY,cascade= {CascadeType.REMOVE,CascadeType.ALL})
	@JoinColumn(name="product_id")
	private Set<Variant> variants;
	
	//@ManyToMany(fetch = FetchType.LAZY, mappedBy = "products", cascade = CascadeType.ALL)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "VENDOR_PRODUCT", joinColumns = {
			@JoinColumn(name = "product_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "vender_id", nullable = false) })
	private Set<InvVendor> vendors;
	
	
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ReceivedOrderDetail> receivedOrderDetails;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<InventoryItem> inventoryItem;
	
	/*
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<PurchaseOrderDetail> purchaseOrderDetail;
	*/
	
	public Product() {
		this.productCode = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();
	}
	
	/**
	 * @return the inventoryItem
	 */
	public List<InventoryItem> getInventoryItem() {
		return inventoryItem;
	}

	/**
	 * @param inventoryItem the inventoryItem to set
	 */
	public void setInventoryItem(List<InventoryItem> inventoryItem) {
		this.inventoryItem = inventoryItem;
	}

	/**
	 * @return the receivedOrderDetails
	 */
	public List<ReceivedOrderDetail> getReceivedOrderDetails() {
		return receivedOrderDetails;
	}

	/**
	 * @param receivedOrderDetails the receivedOrderDetails to set
	 */
	public void setReceivedOrderDetails(List<ReceivedOrderDetail> receivedOrderDetails) {
		this.receivedOrderDetails = receivedOrderDetails;
	}

	/*
	public List<PurchaseOrderDetail> getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}

	*//**
	 * @param purchaseOrderDetail the purchaseOrderDetail to set
	 *//*
	public void setPurchaseOrderDetail(List<PurchaseOrderDetail> purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
	}*/

	
	/**
	 * @return the vendors
	 */
	public Set<InvVendor> getVendors() {
		return vendors;
	}

	/**
	 * @param vendors the vendors to set
	 */
	public void setVendors(Set<InvVendor> vendors) {
		this.vendors = vendors;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productId
	 */
	public Long getProductId() {
		return this.productId;
	}
	
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

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

	/**
	 * @return the subCategory
	 */
	public SubCategory getSubCategory() {
		return subCategory;
	}

	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
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
	 * @return the status
	 */
	public STATUS getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(STATUS status) {
		this.status = status;
	}

	/**
	 * @return the productStatus
	 */
	public PRODUCTSTATUS getProductStatus() {
		return productStatus;
	}

	/**
	 * @param productStatus the productStatus to set
	 */
	public void setProductStatus(PRODUCTSTATUS productStatus) {
		this.productStatus = productStatus;
	}

	/**
	 * @return the variants
	 */
	public Set<Variant> getVariants() {
		return variants;
	}

	/**
	 * @param variants the variants to set
	 */
	public void setVariants(Set<Variant> variants) {
		this.variants = variants;
	}
}
