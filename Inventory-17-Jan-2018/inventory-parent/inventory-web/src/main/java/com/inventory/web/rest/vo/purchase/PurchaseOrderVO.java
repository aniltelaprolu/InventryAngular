package com.inventory.web.rest.vo.purchase;

import java.util.Date;
import java.util.Set;

public class PurchaseOrderVO {
	
	private Long vendorId;
	
	private String vendorName;
	
	private Long purchaseId;

	private String purchaseNO;
	
	private String shippingAddress;
	
	private String billingAddress;
	
	private Date orderDate;
	
	private Date requiredByDate;
	
	Set<PurchaseOrderDetailsVO> listpurchaseDetails;
	
	
	
	public Date getRequiredByDate() {
		return requiredByDate;
	}

	public void setRequiredByDate(Date requiredByDate) {
		this.requiredByDate = requiredByDate;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * @return the vendorId
	 */
	public Long getVendorId() {
		return vendorId;
	}

	/**
	 * @param vendorId the vendorId to set
	 */
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	/**
	 * @return the listpurchaseDetails
	 */
	public Set<PurchaseOrderDetailsVO> getListpurchaseDetails() {
		return listpurchaseDetails;
	}

	/**
	 * @param listpurchaseDetails the listpurchaseDetails to set
	 */
	public void setListpurchaseDetails(Set<PurchaseOrderDetailsVO> listpurchaseDetails) {
		this.listpurchaseDetails = listpurchaseDetails;
	}

	/**
	 * @return the purchaseId
	 */
	public Long getPurchaseId() {
		return purchaseId;
	}

	/**
	 * @param purchaseId the purchaseId to set
	 */
	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	/**
	 * @return the purchaseNO
	 */
	public String getPurchaseNO() {
		return purchaseNO;
	}

	/**
	 * @param purchaseNO the purchaseNO to set
	 */
	public void setPurchaseNO(String purchaseNO) {
		this.purchaseNO = purchaseNO;
	}

	/**
	 * @return the shippingAddress
	 */
	public String getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * @param shippingAddress the shippingAddress to set
	 */
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/**
	 * @return the billingAddress
	 */
	public String getBillingAddress() {
		return billingAddress;
	}

	/**
	 * @param billingAddress the billingAddress to set
	 */
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
}
