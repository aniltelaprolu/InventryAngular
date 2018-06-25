package com.inventory.web.rest.vo.reports;

public class ProductStatsVO {

	private long totalNoOfProducts;
	
	private long totalNoOfActiveProducts;
	
	private long totalNoOfInActiveProducts;
	
	private long totalNoOfAvailableProducts;
	
	private long totalNoOfUnAvailableProducts;
	
	private long totalNoOfBroadcastedProducts;

	/**
	 * @return the totalNoOfProducts
	 */
	public long getTotalNoOfProducts() {
		return totalNoOfProducts;
	}

	/**
	 * @param totalNoOfProducts the totalNoOfProducts to set
	 */
	public void setTotalNoOfProducts(long totalNoOfProducts) {
		this.totalNoOfProducts = totalNoOfProducts;
	}

	/**
	 * @return the totalNoOfActiveProducts
	 */
	public long getTotalNoOfActiveProducts() {
		return totalNoOfActiveProducts;
	}

	/**
	 * @param totalNoOfActiveProducts the totalNoOfActiveProducts to set
	 */
	public void setTotalNoOfActiveProducts(long totalNoOfActiveProducts) {
		this.totalNoOfActiveProducts = totalNoOfActiveProducts;
	}

	/**
	 * @return the totalNoOfInActiveProducts
	 */
	public long getTotalNoOfInActiveProducts() {
		return totalNoOfInActiveProducts;
	}

	/**
	 * @param totalNoOfInActiveProducts the totalNoOfInActiveProducts to set
	 */
	public void setTotalNoOfInActiveProducts(long totalNoOfInActiveProducts) {
		this.totalNoOfInActiveProducts = totalNoOfInActiveProducts;
	}

	/**
	 * @return the totalNoOfAvailableProducts
	 */
	public long getTotalNoOfAvailableProducts() {
		return totalNoOfAvailableProducts;
	}

	/**
	 * @param totalNoOfAvailableProducts the totalNoOfAvailableProducts to set
	 */
	public void setTotalNoOfAvailableProducts(long totalNoOfAvailableProducts) {
		this.totalNoOfAvailableProducts = totalNoOfAvailableProducts;
	}

	/**
	 * @return the totalNoOfUnAvailableProducts
	 */
	public long getTotalNoOfUnAvailableProducts() {
		return totalNoOfUnAvailableProducts;
	}

	/**
	 * @param totalNoOfUnAvailableProducts the totalNoOfUnAvailableProducts to set
	 */
	public void setTotalNoOfUnAvailableProducts(long totalNoOfUnAvailableProducts) {
		this.totalNoOfUnAvailableProducts = totalNoOfUnAvailableProducts;
	}

	/**
	 * @return the totalNoOfBroadcastedProducts
	 */
	public long getTotalNoOfBroadcastedProducts() {
		return totalNoOfBroadcastedProducts;
	}

	/**
	 * @param totalNoOfBroadcastedProducts the totalNoOfBroadcastedProducts to set
	 */
	public void setTotalNoOfBroadcastedProducts(long totalNoOfBroadcastedProducts) {
		this.totalNoOfBroadcastedProducts = totalNoOfBroadcastedProducts;
	}
}
