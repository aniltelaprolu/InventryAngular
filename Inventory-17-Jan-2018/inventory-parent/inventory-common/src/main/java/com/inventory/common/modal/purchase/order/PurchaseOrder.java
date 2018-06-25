package com.inventory.common.modal.purchase.order;

import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.inventory.common.constants.AppConstants.POSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.base.BaseAuditModel;
import com.inventory.common.modal.receive.order.ReceivedOrder;
import com.inventory.common.modal.vendor.InvVendor;

@Entity
@Table(name="purchase_order")
public class PurchaseOrder extends BaseAuditModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3528835584503928521L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="purchase_order_id")
	private Long purchaseOrderId;
	
	@Column(name="purchase_order_no")
	private String purchaseOrderNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="order_date")
	private Date orderDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="requiredByDate")
	private Date requiredByDate;
	
	@Column(name="shipping_address")
	private String shippingAddress;
	
	@Column(name="billing_address")
	private String billingAddress;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "vender_id",nullable=false)
	private InvVendor invVendor;
	
	@Enumerated(EnumType.STRING)
	@Column(name="po_status")
	private POSTATUS poStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private STATUS status;
	
/*	@Column(name="total_quantity")
	private int totalQuantity;*/
	
	
/*	@OneToMany(targetEntity=PurchaseOrderDetail.class,fetch=FetchType.LAZY,cascade= {CascadeType.REMOVE,CascadeType.ALL})
	@JoinColumn(name="purchase_order_id")
	private Set<PurchaseOrderDetail> purchaseOrderDetail;*/
	
	

	@OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
	private Set<PurchaseOrderDetail> purchaseOrderDetail;
	
	@OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
	private List<ReceivedOrder> reveivedOrder;
	
	public Date getRequiredByDate() {
		return requiredByDate;
	}

	public void setRequiredByDate(Date requiredByDate) {
		this.requiredByDate = requiredByDate;
	}

	public List<ReceivedOrder> getReveivedOrder() {
		return reveivedOrder;
	}

	public void setReveivedOrder(List<ReceivedOrder> reveivedOrder) {
		this.reveivedOrder = reveivedOrder;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	/**
	 * @return the purchaseOrderDetail
	 */
	public Set<PurchaseOrderDetail> getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}

	/**
	 * @param purchaseOrderDetail the purchaseOrderDetail to set
	 */
	public void setPurchaseOrderDetail(Set<PurchaseOrderDetail> purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
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

	
	/**
	 * @return the purchaseOrderNo
	 */
	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	/**
	 * @param purchaseOrderNo the purchaseOrderNo to set
	 */
	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
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

	/*
	public Set<PurchaseOrderDetail> getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}

	*//**
	 * @param purchaseOrderDetail the purchaseOrderDetail to set
	 *//*
	public void setPurchaseOrderDetail(Set<PurchaseOrderDetail> purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
	}*/
}
