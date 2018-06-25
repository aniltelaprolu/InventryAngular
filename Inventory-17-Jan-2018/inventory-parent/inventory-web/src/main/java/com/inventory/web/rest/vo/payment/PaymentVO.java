/**
 * 
 */
package com.inventory.web.rest.vo.payment;

import com.inventory.common.constants.AppConstants.INVENTORYPAYMENTSTATUS;
import com.inventory.common.constants.AppConstants.PAYBY;
import com.inventory.common.constants.AppConstants.POSTATUS;

/**
 * @author ES003
 *
 */
public class PaymentVO {
	
	private Long inventoryPaymentId;
	
	private Long vendorId;
	
	private Long companyBankId;
	
	private String vendorName;
	
	private Long vendorAccountId;
 
	private POSTATUS poStatus;
	
	private PAYBY payBy;
	
	private Long chequeNo;
	
	private Double totalAmount;
	
	private Double payAmount;
	
	private Long receivedOrderId;
	
	private Long purchaseOrderId;
	
	private INVENTORYPAYMENTSTATUS inventoryPaymentStatus;
	
	/**
	 * @return the companyBankId
	 */
	public Long getCompanyBankId() {
		return companyBankId;
	}

	/**
	 * @param companyBankId the companyBankId to set
	 */
	public void setCompanyBankId(Long companyBankId) {
		this.companyBankId = companyBankId;
	}

	/**
	 * @return the chequeNo
	 */
	public Long getChequeNo() {
		return chequeNo;
	}

	/**
	 * @param chequeNo the chequeNo to set
	 */
	public void setChequeNo(Long chequeNo) {
		this.chequeNo = chequeNo;
	}

	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * @param vendorName the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * @return the inventoryPaymentStatus
	 */
	public INVENTORYPAYMENTSTATUS getInventoryPaymentStatus() {
		return inventoryPaymentStatus;
	}

	/**
	 * @param inventoryPaymentStatus the inventoryPaymentStatus to set
	 */
	public void setInventoryPaymentStatus(INVENTORYPAYMENTSTATUS inventoryPaymentStatus) {
		this.inventoryPaymentStatus = inventoryPaymentStatus;
	}

	/**
	 * @return the inventoryPaymentId
	 */
	public Long getInventoryPaymentId() {
		return inventoryPaymentId;
	}

	/**
	 * @param inventoryPaymentId the inventoryPaymentId to set
	 */
	public void setInventoryPaymentId(Long inventoryPaymentId) {
		this.inventoryPaymentId = inventoryPaymentId;
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

	public Long getVendorAccountId() {
		return vendorAccountId;
	}

	public void setVendorAccountId(Long vendorAccountId) {
		this.vendorAccountId = vendorAccountId;
	}

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
	 * @return the payBy
	 */
	public PAYBY getPayBy() {
		return payBy;
	}

	/**
	 * @param payBy the payBy to set
	 */
	public void setPayBy(PAYBY payBy) {
		this.payBy = payBy;
	}

	/**
	 * @return the totalAmount
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the payAmount
	 */
	public Double getPayAmount() {
		return payAmount;
	}

	/**
	 * @param payAmount the payAmount to set
	 */
	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	/**
	 * @return the receivedOrderId
	 */
	public Long getReceivedOrderId() {
		return receivedOrderId;
	}

	/**
	 * @param receivedOrderId the receivedOrderId to set
	 */
	public void setReceivedOrderId(Long receivedOrderId) {
		this.receivedOrderId = receivedOrderId;
	}

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
	
	
	
}
