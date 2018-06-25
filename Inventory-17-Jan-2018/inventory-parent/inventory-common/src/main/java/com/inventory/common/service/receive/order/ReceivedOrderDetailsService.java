/**
 * 
 */
package com.inventory.common.service.receive.order;

import java.util.List;

/**
 * @author ES003
 *
 */
public interface ReceivedOrderDetailsService {
	public List<Object[]> getOrderDetails(Long receivedOrderId);
	
	List<Object[]> getTotalAmountDetails(Long receivedOrderId);
}
