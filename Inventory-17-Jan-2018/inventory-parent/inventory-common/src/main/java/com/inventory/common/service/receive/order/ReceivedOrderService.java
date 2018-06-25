/**
 * 
 */
package com.inventory.common.service.receive.order;

import java.util.List;

import com.inventory.common.modal.receive.order.ReceivedOrder;

/**
 * @author ES003
 *
 */
public interface ReceivedOrderService {
	
	public ReceivedOrder saveReceiveOrderData(ReceivedOrder receivedOrder);
	
	//int getTotalQuantityByReceivedOrderId(Long receivedOrderId);
	
	List<Object[]> getAllReceivedOrderNumber(Long purchaseOrderId);
	
	public ReceivedOrder findById(Long receivedOrderId); 
	
	public void updateDueAmount(Double dueAmount,Long receivedOrderId);

	Double getTotalDueAmount(Long receivedOrderId);
}
