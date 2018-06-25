package com.inventory.common.modal.receive.order;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.inventory.common.constants.AppConstants.ROSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.base.BaseAuditModel;
import com.inventory.common.modal.purchase.order.PurchaseOrder;
import com.inventory.common.modal.purchase.order.PurchaseOrderDetail;
import com.inventory.common.modal.vendor.InvVendor;

@Entity
@Table(name="received_order")
public class ReceivedOrder extends BaseAuditModel{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="received_order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long receivedOrderId;
	
	@Column(name="received_order_no")
	private String receviedOrderNo;
	
	@Column(name="receive_order_date")
	private Date receivedOrderDate;
	
	@Column(name="shipping_date")
	private Date shippingDate;
	
	@Column(name="shipping_address")
	private String shippingAddress;
	
	@Column(name="billing_address")
	private String billingAddress;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "vender_id")
	private InvVendor invVendor;
	
	@Enumerated(EnumType.STRING)
	@Column(name="received_order_status")
	private ROSTATUS roStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private STATUS status;
	
	/*@Column(name="total_quantity")
	private int totalQuantity;*/
	
	@Column(name="due_amount")
	private Double dueAmount;
	

	/*@OneToMany(targetEntity=ReceivedOrderDetail.class,fetch=FetchType.LAZY,cascade= {CascadeType.REMOVE,CascadeType.ALL})
	@JoinColumn(name="received_order_id")
	private Set<ReceivedOrderDetail> receivedOrderDetail;*/
	
	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}


	@OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
	private Set<PurchaseOrderDetail> purchaseOrderDetail;
	

	@OneToMany(mappedBy = "receivedOrder", cascade = CascadeType.ALL)
	private Set<ReceivedOrderDetail> receivedOrderDetail;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "purchase_order_id")
	private PurchaseOrder purchaseOrder;
	
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
	 * @return the roStatus
	 */
	public ROSTATUS getRoStatus() {
		return roStatus;
	}

	/**
	 * @param roStatus the roStatus to set
	 */
	public void setRoStatus(ROSTATUS roStatus) {
		this.roStatus = roStatus;
	}

	public Set<PurchaseOrderDetail> getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}

	public void setPurchaseOrderDetail(Set<PurchaseOrderDetail> purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
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
	 * @return the receviedOrderNo
	 */
	public String getReceviedOrderNo() {
		return receviedOrderNo;
	}

	/**
	 * @param receviedOrderNo the receviedOrderNo to set
	 */
	public void setReceviedOrderNo(String receviedOrderNo) {
		this.receviedOrderNo = receviedOrderNo;
	}

	/**
	 * @return the receivedOrderDate
	 */
	public Date getReceivedOrderDate() {
		return receivedOrderDate;
	}

	/**
	 * @param receivedOrderDate the receivedOrderDate to set
	 */
	public void setReceivedOrderDate(Date receivedOrderDate) {
		this.receivedOrderDate = receivedOrderDate;
	}

	/**
	 * @return the shippingDate
	 */
	public Date getShippingDate() {
		return shippingDate;
	}

	/**
	 * @param shippingDate the shippingDate to set
	 */
	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
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
	 * @return the receivedOrderDetail
	 */
	public Set<ReceivedOrderDetail> getReceivedOrderDetail() {
		return receivedOrderDetail;
	}

	/**
	 * @param receivedOrderDetail the receivedOrderDetail to set
	 */
	public void setReceivedOrderDetail(Set<ReceivedOrderDetail> receivedOrderDetail) {
		this.receivedOrderDetail = receivedOrderDetail;
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
}
