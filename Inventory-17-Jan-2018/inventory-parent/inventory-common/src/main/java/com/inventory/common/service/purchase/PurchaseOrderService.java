/**
 * 
 */
package com.inventory.common.service.purchase;


import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.inventory.common.constants.AppConstants.POSTATUS;
import com.inventory.common.modal.purchase.order.PurchaseOrder;

/**
 * @author ES003
 *
 */

public interface PurchaseOrderService {
	public PurchaseOrder savepurchaseData(PurchaseOrder purchaseOrder);

	public List<PurchaseOrder> getAll();
	
	public PurchaseOrder findByPurchaseOrderId(Long purchaseOrderId);
	
	void updatePurchaseOrderStatus(Long purchaseOrderId,POSTATUS status);
	
	//int getTotalQuantityByPurchaseOrderId(Long purchaseOrderId);
	
	List<Object[]> getPurchaseOrderStatus(Long vendorId);
	
	List<Object[]> getPurchaseOrderNoByStatus(POSTATUS poStatus);

	public List<PurchaseOrder> findAll(PageRequest pageRequest);
	
}
