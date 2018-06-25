package com.inventory.common.modal.payment;

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

import com.inventory.common.constants.AppConstants.INVENTORYPAYMENTSTATUS;
import com.inventory.common.constants.AppConstants.PAYBY;
import com.inventory.common.constants.AppConstants.POSTATUS;
import com.inventory.common.modal.base.BaseAuditModel;
import com.inventory.common.modal.company.profile.CompanyBank;
import com.inventory.common.modal.purchase.order.PurchaseOrder;
import com.inventory.common.modal.receive.order.ReceivedOrder;
import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.modal.vendor.VendorBankAccountDetails;

@Entity
@Table(name="inventory_payment")
public class InventoryPayment extends BaseAuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7875914616040921358L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "inventory_payment_id")
	private Long inventoryPaymentId;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "vendor_id")
	private InvVendor invVendor;
	
	@ManyToOne(optional = true)
    @JoinColumn(name = "vendor_account_id",nullable=true)
	private VendorBankAccountDetails vendorBankAccountDetails;
	
	@ManyToOne(optional = true)
    @JoinColumn(name = "company_bank_id",nullable=true)
	private CompanyBank companyBank;
	
	@Enumerated(EnumType.STRING)
	@Column(name="po_status")
	private POSTATUS poStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name="pay_by")
	private PAYBY payBy;
	
	@Column(name="cheque_number")
	private Long chequeNo;
	
	@Column(name="total_amount")
	private Double totalAmount;
	
	@Column(name="pay_amount")
	private Double payAmount;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "received_order_id")
	private ReceivedOrder receivedOrder;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "purchase_order_id")
	private PurchaseOrder purchaseOrder;
	
	@Enumerated(EnumType.STRING)
	@Column(name="inventory_payment_status")
	private INVENTORYPAYMENTSTATUS inventoryPaymentStatus;

	
	/**
	 * @return the companyBank
	 */
	public CompanyBank getCompanyBank() {
		return companyBank;
	}

	/**
	 * @param companyBank the companyBank to set
	 */
	public void setCompanyBank(CompanyBank companyBank) {
		this.companyBank = companyBank;
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
	 * @return the purchaseOrder
	 */
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	/**
	 * @param purchaseOrder the purchaseOrder to set
	 */
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
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
	 * @return the vendorBankAccountDetails
	 */
	public VendorBankAccountDetails getVendorBankAccountDetails() {
		return vendorBankAccountDetails;
	}

	/**
	 * @param vendorBankAccountDetails the vendorBankAccountDetails to set
	 */
	public void setVendorBankAccountDetails(VendorBankAccountDetails vendorBankAccountDetails) {
		this.vendorBankAccountDetails = vendorBankAccountDetails;
	}

}
