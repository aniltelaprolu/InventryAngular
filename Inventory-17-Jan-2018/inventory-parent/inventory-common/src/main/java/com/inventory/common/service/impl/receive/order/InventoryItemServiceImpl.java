
package com.inventory.common.service.impl.receive.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.modal.receive.order.InventoryItem;
import com.inventory.common.repository.receive.order.InventoryItemRepository;
import com.inventory.common.service.receive.order.InventoryItemService;

/**
 * @author ES002
 *
 */
@Service("inventoryItemService")
public class InventoryItemServiceImpl implements InventoryItemService {
	
	@Autowired
	private InventoryItemRepository inventoryItemRepository;

	/* (non-Javadoc)
	 * @see com.inventory.common.service.receive.order.InventoryItemService#getItemQuantity(java.lang.Long, java.lang.Long)
	 */
	@Override
	public int getItemQuantity(Long productId, Long variantId) {
		return inventoryItemRepository.findQuantityByProductIdAndVariantId(productId, variantId);
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.receive.order.InventoryItemService#updateInventoryOfferId(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	
	@Transactional
	@Override
	public void updateInventoryOfferId(Long offerDetailId, Long productId, Long variantId,Long inventoryItemId) {
		inventoryItemRepository.updateOfferId(offerDetailId, productId,variantId,inventoryItemId);
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.receive.order.InventoryItemService#saveInventoryItemData(com.inventory.common.modal.receive.order.InventoryItem)
	 */
	@Override
	public InventoryItem saveInventoryItemData(InventoryItem item) {
		return inventoryItemRepository.save(item);
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.receive.order.InventoryItemService#getAllDetails(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Object[]> getAllDetails(Long productId, Long variantId) {
		return inventoryItemRepository.getAllDetailsByProductIdAndVariantId(productId, variantId);
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.receive.order.InventoryItemService#updateOfferIdToNull(java.lang.Long, java.lang.Long)
	 */
	@Transactional
	@Override
	public void updateOfferIdToNull(Long productId, Long variantId) {
		inventoryItemRepository.updateOfferIdToNull(productId, variantId);
	}

	
}
