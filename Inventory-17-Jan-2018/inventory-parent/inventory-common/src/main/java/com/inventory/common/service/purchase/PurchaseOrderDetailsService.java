/**
 * 
 */
package com.inventory.common.service.purchase;

import java.util.List;

import com.inventory.common.modal.purchase.order.PurchaseOrderDetail;

/**
 * @author ES001
 *
 */
public interface PurchaseOrderDetailsService {
	public PurchaseOrderDetail saveData(PurchaseOrderDetail purchaseOrderDetail);
	
	int getAllQuantityDetails(Long purchaseOrderId,Long productId,Long variantId);
	
	public void updateLeftQuantityByPurchaseOrderId(Integer leftQuantity,Long purchaseOrderId,Long productId,Long variantId);
	
	List<Integer> getAllLeftQuantity(Long purchaseOrderId);
}
