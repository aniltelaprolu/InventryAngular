package com.inventory.common.modal.purchase.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.inventory.common.modal.base.BaseAuditModel;
import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.Variant;
import com.inventory.common.modal.vendor.InvVendor;

@Entity
@Table(name="purchase_order_detail")
public class PurchaseOrderDetail extends BaseAuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3528835584503928521L;

	@Id
	@Column(name="purchase_order_detail_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long purchaseOrderDetailId;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="left_quantity")
	private Integer leftQuantity;
	
	
	/*@ManyToOne(optional = false)
    @JoinColumn(name = "vender_id")
	private InvVendor invVendor;*/
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "variant_id")
	private Variant variant;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "purchase_order_id",nullable=false)
	private PurchaseOrder purchaseOrder;
	
	
	public Integer getLeftQuantity() {
		return leftQuantity;
	}

	public void setLeftQuantity(Integer leftQuantity) {
		this.leftQuantity = leftQuantity;
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
	 * @return the purchaseOrderDetailId
	 */
	public Long getPurchaseOrderDetailId() {
		return purchaseOrderDetailId;
	}

	/**
	 * @param purchaseOrderDetailId the purchaseOrderDetailId to set
	 */
	public void setPurchaseOrderDetailId(Long purchaseOrderDetailId) {
		this.purchaseOrderDetailId = purchaseOrderDetailId;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the invVendor
	 */
	/*public InvVendor getInvVendor() {
		return invVendor;
	}

	*//**
	 * @param invVendor the invVendor to set
	 *//*
	public void setInvVendor(InvVendor invVendor) {
		this.invVendor = invVendor;
	}*/

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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((variant == null) ? 0 : variant.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchaseOrderDetail other = (PurchaseOrderDetail) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (variant == null) {
			if (other.variant != null)
				return false;
		} else if (!variant.equals(other.variant))
			return false;
		return true;
	}
	
}
