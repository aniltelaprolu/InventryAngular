/**
 * 
 */
package com.inventory.web.rest.vo.received;

import java.util.Date;
import java.util.Set;

/**
 * @author ES003
 *
 */
public class ReceivedOrderVO {
	
	private Long vendorId;
	
	private Long purchaseOrderId;
	
	private Long purchaseId;

	private String shippingAddress;
	
	private String billingAddress;
	
	private Date receivedOrderDate;
	
	private Date shippingDate;
	
	Set<ReceivedOrderDetailsVO> listReceivedDetails;

	/**
	 * @return the purchaseOrderId
	 */
	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	/**
	 * @param purchaseOrderId the purchaseOrderId to set
	 */
	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Date getReceivedOrderDate() {
		return receivedOrderDate;
	}

	public void setReceivedOrderDate(Date receivedOrderDate) {
		this.receivedOrderDate = receivedOrderDate;
	}

	public Set<ReceivedOrderDetailsVO> getListReceivedDetails() {
		return listReceivedDetails;
	}

	public void setListReceivedDetails(Set<ReceivedOrderDetailsVO> listReceivedDetails) {
		this.listReceivedDetails = listReceivedDetails;
	}
	
	
}
