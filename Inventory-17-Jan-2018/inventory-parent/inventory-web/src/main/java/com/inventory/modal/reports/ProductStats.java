package com.inventory.modal.reports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.base.BaseAuditModel;

@Entity
@Table(name = "product_stats")
public class ProductStats extends BaseAuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2624853753258138126L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "product_stat_id")
	private Long productStatId;
	
	@Column(name = "total_no_of_products")
	private Long totalNoOfProducts;
	
	@Column(name = "total_no_of_active_products")
	private Long totalNoOfActiveProducts;
	
	@Column(name = "total_no_of_inactive_products")
	private Long totalNoOfInActiveProducts;
	
	@Column(name = "total_no_of_available_products")
	private Long totalNoOfAvailableProducts;
	
	@Column(name = "total_no_of_unavailable_products")
	private Long totalNoOfUnAvailableProducts;
	
	@Column(name = "total_no_of_broadcasted_products")
	private Long totalNoOfBroadcastedProducts;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private STATUS status;

	/**
	 * @return the productStatId
	 */
	public Long getProductStatId() {
		return productStatId;
	}

	/**
	 * @param productStatId the productStatId to set
	 */
	public void setProductStatId(Long productStatId) {
		this.productStatId = productStatId;
	}

	/**
	 * @return the totalNoOfProducts
	 */
	public Long getTotalNoOfProducts() {
		return totalNoOfProducts;
	}

	/**
	 * @param totalNoOfProducts the totalNoOfProducts to set
	 */
	public void setTotalNoOfProducts(Long totalNoOfProducts) {
		this.totalNoOfProducts = totalNoOfProducts;
	}

	/**
	 * @return the totalNoOfActiveProducts
	 */
	public Long getTotalNoOfActiveProducts() {
		return totalNoOfActiveProducts;
	}

	/**
	 * @param totalNoOfActiveProducts the totalNoOfActiveProducts to set
	 */
	public void setTotalNoOfActiveProducts(Long totalNoOfActiveProducts) {
		this.totalNoOfActiveProducts = totalNoOfActiveProducts;
	}

	/**
	 * @return the totalNoOfInActiveProducts
	 */
	public Long getTotalNoOfInActiveProducts() {
		return totalNoOfInActiveProducts;
	}

	/**
	 * @param totalNoOfInActiveProducts the totalNoOfInActiveProducts to set
	 */
	public void setTotalNoOfInActiveProducts(Long totalNoOfInActiveProducts) {
		this.totalNoOfInActiveProducts = totalNoOfInActiveProducts;
	}

	/**
	 * @return the totalNoOfAvailableProducts
	 */
	public Long getTotalNoOfAvailableProducts() {
		return totalNoOfAvailableProducts;
	}

	/**
	 * @param totalNoOfAvailableProducts the totalNoOfAvailableProducts to set
	 */
	public void setTotalNoOfAvailableProducts(Long totalNoOfAvailableProducts) {
		this.totalNoOfAvailableProducts = totalNoOfAvailableProducts;
	}

	/**
	 * @return the totalNoOfUnAvailableProducts
	 */
	public Long getTotalNoOfUnAvailableProducts() {
		return totalNoOfUnAvailableProducts;
	}

	/**
	 * @param totalNoOfUnAvailableProducts the totalNoOfUnAvailableProducts to set
	 */
	public void setTotalNoOfUnAvailableProducts(Long totalNoOfUnAvailableProducts) {
		this.totalNoOfUnAvailableProducts = totalNoOfUnAvailableProducts;
	}

	/**
	 * @return the totalNoOfBroadcastedProducts
	 */
	public Long getTotalNoOfBroadcastedProducts() {
		return totalNoOfBroadcastedProducts;
	}

	/**
	 * @param totalNoOfBroadcastedProducts the totalNoOfBroadcastedProducts to set
	 */
	public void setTotalNoOfBroadcastedProducts(Long totalNoOfBroadcastedProducts) {
		this.totalNoOfBroadcastedProducts = totalNoOfBroadcastedProducts;
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
}
