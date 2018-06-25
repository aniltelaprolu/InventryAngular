package com.inventory.common.modal.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.base.BaseAuditModel;
import com.inventory.common.modal.receive.order.ReceivedOrderDetail;

@Entity
@Table(name = "variant")
public class Variant extends BaseAuditModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "variant_id")
	private Long variantId;

	@Column(name = "variant_name")
	private String variantName;

	@Column(name = "price")
	private Double price;

	@Column(name = "quantity")
	private Integer quantity;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private STATUS status;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product products;

	/*@OneToMany(targetEntity = Items.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "variant_id")
	private List<Items> items;*/

	/*@OneToMany(targetEntity = Parameter.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "variant_id")
	private List<Parameter> parameter;*/
	
	@OneToMany(targetEntity=VariantImage.class,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="variant_id")
	private List<VariantImage> variantImages;
	
	@OneToMany(targetEntity = VariantParameter.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "variant_id")
	private List<VariantParameter> variantParameters;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ReceivedOrderDetail> receivedOrderDetails;
	/*
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<PurchaseOrderDetail> purchaseOrderDetail;*/

	/**
	 * @return the variantId
	 */
	public Long getVariantId() {
		return variantId;
	}

	/**
	 * @param variantId the variantId to set
	 */
	public void setVariantId(Long variantId) {
		this.variantId = variantId;
	}

	/**
	 * @return the variantName
	 */
	public String getVariantName() {
		return variantName;
	}

	/**
	 * @param variantName the variantName to set
	 */
	public void setVariantName(String variantName) {
		this.variantName = variantName;
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
	 * @return the products
	 */
	public Product getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(Product products) {
		this.products = products;
	}

	/**
	 * @return the variantImages
	 */
	public List<VariantImage> getVariantImages() {
		return variantImages;
	}

	/**
	 * @param variantImages the variantImages to set
	 */
	public void setVariantImages(List<VariantImage> variantImages) {
		this.variantImages = variantImages;
	}

	/**
	 * @return the variantParameters
	 */
	public List<VariantParameter> getVariantParameters() {
		return variantParameters;
	}

	/**
	 * @param variantParameters the variantParameters to set
	 */
	public void setVariantParameters(List<VariantParameter> variantParameters) {
		this.variantParameters = variantParameters;
	}

	/**
	 * @return the receivedOrderDetails
	 */
	public List<ReceivedOrderDetail> getReceivedOrderDetails() {
		return receivedOrderDetails;
	}

	/**
	 * @param receivedOrderDetails the receivedOrderDetails to set
	 */
	public void setReceivedOrderDetails(List<ReceivedOrderDetail> receivedOrderDetails) {
		this.receivedOrderDetails = receivedOrderDetails;
	}

	/*
	public List<PurchaseOrderDetail> getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}

	*//**
	 * @param purchaseOrderDetail the purchaseOrderDetail to set
	 *//*
	public void setPurchaseOrderDetail(List<PurchaseOrderDetail> purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
	}*/
	

	
}
