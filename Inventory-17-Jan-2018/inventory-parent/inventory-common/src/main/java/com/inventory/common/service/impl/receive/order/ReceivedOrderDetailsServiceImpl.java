/**
 * 
 */
package com.inventory.common.service.impl.receive.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.common.repository.receive.order.ReceivedOrderDetailsRepository;
import com.inventory.common.service.receive.order.ReceivedOrderDetailsService;

/**
 * @author ES003
 *
 */
@Service("receivedOrderDetailsService")
public class ReceivedOrderDetailsServiceImpl implements ReceivedOrderDetailsService {
	
	@Autowired
	private ReceivedOrderDetailsRepository receivedOrderDetailsRepository;

	/* (non-Javadoc)
	 * @see com.inventory.common.service.receive.order.ReceivedOrderDetailsService#getOrderDetails()
	 */
	@Override
	public List<Object[]> getOrderDetails(Long receivedOrderId) {
		List<Object[]> listData = receivedOrderDetailsRepository.getOrderDetails(receivedOrderId);
		System.out.println(listData);
		return listData;
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.receive.order.ReceivedOrderDetailsService#getTotalAmountDetails(java.lang.Long)
	 */
	@Override
	public List<Object[]> getTotalAmountDetails(Long receivedOrderId) {
		return receivedOrderDetailsRepository.getTotalAmountDetails(receivedOrderId);
	}
	
	
}
