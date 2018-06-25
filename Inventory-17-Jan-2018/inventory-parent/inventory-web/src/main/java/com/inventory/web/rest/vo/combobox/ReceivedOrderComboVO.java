/**
 * 
 */
package com.inventory.web.rest.vo.combobox;

/**
 * @author ES003
 *
 */
public class ReceivedOrderComboVO {
	
	private Long receivedOrderId;
	
	private String receivedOrderNo;
	
	private Double totalAmount;
	
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
	 * @return the receivedOrderNo
	 */
	public String getReceivedOrderNo() {
		return receivedOrderNo;
	}

	/**
	 * @param receivedOrderNo the receivedOrderNo to set
	 */
	public void setReceivedOrderNo(String receivedOrderNo) {
		this.receivedOrderNo = receivedOrderNo;
	}
	
	
	
}
