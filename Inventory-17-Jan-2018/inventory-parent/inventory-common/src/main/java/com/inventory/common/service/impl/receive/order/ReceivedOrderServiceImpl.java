/**
 * 
 */
package com.inventory.common.service.impl.receive.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.modal.receive.order.ReceivedOrder;
import com.inventory.common.repository.receive.order.ReceivedOrderRepository;
import com.inventory.common.service.receive.order.ReceivedOrderService;

/**
 * @author ES003
 *
 */

@Service("receivedOrderService")
public class ReceivedOrderServiceImpl implements ReceivedOrderService {
	
	@Autowired
	private ReceivedOrderRepository receivedOrderRepository;

	/* (non-Javadoc)
	 * @see com.inventory.common.service.receive.order.ReceivedOrderService#saveReceiveOrderData(com.inventory.common.modal.receive.order.ReceivedOrder)
	 */
	@Override
	public ReceivedOrder saveReceiveOrderData(ReceivedOrder receivedOrder) {
		return receivedOrderRepository.save(receivedOrder);
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.receive.order.ReceivedOrderService#getAllReceivedOrderNumber(java.lang.Long)
	 */
	@Override
	public List<Object[]> getAllReceivedOrderNumber(Long purchaseOrderId) {
		return receivedOrderRepository.getReceivedOrderNumber(purchaseOrderId);
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.receive.order.ReceivedOrderService#findById(java.lang.Long)
	 */
	@Override
	public ReceivedOrder findById(Long receivedOrderId) {
		return receivedOrderRepository.getOne(receivedOrderId);
	}
	
	@Override
	public Double getTotalDueAmount(Long receivedOrderId) {
		return receivedOrderRepository.getTotalDueAmount(receivedOrderId);
	}

	@Transactional
	@Override
	public void updateDueAmount(Double dueAmount,Long receivedOrderId) {
		receivedOrderRepository.updateDueAmountByReceivedOrderId(dueAmount, receivedOrderId);
	}

}
