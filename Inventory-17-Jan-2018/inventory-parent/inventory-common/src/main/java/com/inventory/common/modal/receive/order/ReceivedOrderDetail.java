package com.inventory.common.modal.receive.order;

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
@Table(name="received_order_detail")
public class ReceivedOrderDetail extends BaseAuditModel{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="received_order_detail_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long receivedOrderId;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name = "price")
	private Double price;
	
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
    @JoinColumn(name = "received_order_id")
	private ReceivedOrder receivedOrder;
	
	

	public ReceivedOrder getReceivedOrder() {
		return receivedOrder;
	}

	public void setReceivedOrder(ReceivedOrder receivedOrder) {
		this.receivedOrder = receivedOrder;
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
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}


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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((variant == null) ? 0 : variant.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReceivedOrderDetail other = (ReceivedOrderDetail) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
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
