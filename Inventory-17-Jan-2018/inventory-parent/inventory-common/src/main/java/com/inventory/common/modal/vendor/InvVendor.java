package com.inventory.common.modal.vendor;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.inventory.common.modal.base.BaseAuditModel;
import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.purchase.order.PurchaseOrder;
import com.inventory.common.modal.receive.order.InventoryItem;
import com.inventory.common.modal.receive.order.ReceivedOrder;

/**
 * @author ES002
 *
 */
@Entity
@Table(name = "vendor")
public class InvVendor extends BaseAuditModel {

	private static final long serialVersionUID = -7843482539993301424L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vender_id")
	private Long venderId;

	@Column(name = "ven_id")
	private String venId;

	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;

	@OneToMany(targetEntity = VendorAddress.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "vender_id", nullable = false)
	private List<VendorAddress> venAddress;

	@OneToMany(targetEntity = VendorPhone.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "vender_id", nullable = false)
	private Set<VendorPhone> phones;

	/*
	 * 
	 *  Many to many Relation between vendor and Product
	 * 
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "VENDOR_PRODUCT", joinColumns = {
			@JoinColumn(name = "vender_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "product_id", nullable = false) })
	private Set<Product> products;
	
	@OneToMany(targetEntity = VendorBankAccountDetails.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "vender_id", nullable = false)
	private List<VendorBankAccountDetails> vendorBankDetails;
	

	@OneToMany(mappedBy = "invVendor", cascade = CascadeType.ALL)
	private List<ReceivedOrder> reveivedOrder;
	
	
	@OneToMany(mappedBy = "invVendor", cascade = CascadeType.ALL)
	private List<PurchaseOrder> purchaseOrder;
	
	@OneToMany(mappedBy = "invVendor", cascade = CascadeType.ALL)
	private List<InventoryItem> inventoryItem;
	

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

	public List<VendorBankAccountDetails> getVendorBankDetails() {
		return vendorBankDetails;
	}

	public void setVendorBankDetails(List<VendorBankAccountDetails> vendorBankDetails) {
		this.vendorBankDetails = vendorBankDetails;
	}
	
	
	public List<ReceivedOrder> getReveivedOrder() {
		return reveivedOrder;
	}

	public void setReveivedOrder(List<ReceivedOrder> reveivedOrder) {
		this.reveivedOrder = reveivedOrder;
	}

	/**
	 * @return the purchaseOrder
	 */
	public List<PurchaseOrder> getPurchaseOrder() {
		return purchaseOrder;
	}

	/**
	 * @param purchaseOrder the purchaseOrder to set
	 */
	public void setPurchaseOrder(List<PurchaseOrder> purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	/**
	 * 
	 * @return
	 */
	public Set<Product> getProducts() {
		return products;
	}

	/**
	 * 
	 * @param products
	 */
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public String getVenId() {
		return venId;
	}

	public void setVenId(String venId) {
		this.venId = venId;
	}

	public Long getVenderId() {
		return venderId;
	}

	public void setVenderId(Long venderId) {
		this.venderId = venderId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VendorAddress> getVenAddress() {
		return venAddress;
	}

	public void setVenAddress(List<VendorAddress> venAddress) {
		this.venAddress = venAddress;
	}

	public Set<VendorPhone> getPhones() {
		return phones;
	}

	public void setPhones(Set<VendorPhone> phones) {
		this.phones = phones;
	}

}
