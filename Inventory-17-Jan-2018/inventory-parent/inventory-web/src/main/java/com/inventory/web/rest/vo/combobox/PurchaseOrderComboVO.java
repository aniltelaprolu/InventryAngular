/**
 * 
 */
package com.inventory.web.rest.vo.combobox;

import com.inventory.common.constants.AppConstants.POSTATUS;

/**
 * @author ES003
 *
 */
public class PurchaseOrderComboVO {
	private Long purchaseId;
	
	private String purchaseNO;
	
	private String shippingAddress;
	
	private String billingAddress;
	
	private POSTATUS poStatus;
	

	/**
	 * @return the poStatus
	 */
	public POSTATUS getPoStatus() {
		return poStatus;
	}

	/**
	 * @param poStatus the poStatus to set
	 */
	public void setPoStatus(POSTATUS poStatus) {
		this.poStatus = poStatus;
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
	
	
}
