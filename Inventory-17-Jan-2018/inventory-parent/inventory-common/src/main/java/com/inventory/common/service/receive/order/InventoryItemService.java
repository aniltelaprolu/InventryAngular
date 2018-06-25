/**
 * 
 */
package com.inventory.common.service.receive.order;

import java.util.List;

import com.inventory.common.modal.receive.order.InventoryItem;

/**
 * @author ES002
 *
 */
public interface InventoryItemService {
	int getItemQuantity(Long productId,Long variantId);
	
	void updateInventoryOfferId(Long offerDetailId,Long productId,Long variantId,Long inventoryItemId);
	
	public InventoryItem saveInventoryItemData(InventoryItem item);
	
	public List<Object[]> getAllDetails(Long productId,Long variantId);
	
	void updateOfferIdToNull(Long productId,Long variantId);
}
