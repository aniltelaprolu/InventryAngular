/**
 * 
 */
package com.inventory.common.service.impl.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.modal.purchase.order.PurchaseOrderDetail;
import com.inventory.common.repository.purchase.PurchaseOrderDetailsRepository;
import com.inventory.common.service.purchase.PurchaseOrderDetailsService;

/**
 * @author ES001
 *
 */
@Service("purchaseOrderDetailsService")
public class PurchaseOrderDetailsServiceImpl implements PurchaseOrderDetailsService {
	
	@Autowired
	private PurchaseOrderDetailsRepository purchaseOrderDetailsRepository;

	/* (non-Javadoc)
	 * @see com.inventory.common.service.purchase.PurchaseOrderDetailsService#saveData(com.inventory.common.modal.purchase.order.PurchaseOrderDetail)
	 */
	@Override
	public PurchaseOrderDetail saveData(PurchaseOrderDetail purchaseOrderDetail) {
		return purchaseOrderDetailsRepository.save(purchaseOrderDetail);
	}

	@Override
	public int getAllQuantityDetails(Long purchaseOrderId,Long productId,Long variantId) {
		return purchaseOrderDetailsRepository.getAllQuantityDetails(purchaseOrderId,productId,variantId);
	}

	@Transactional
	@Override
	public void updateLeftQuantityByPurchaseOrderId(Integer leftQuantity, Long purchaseOrderId, Long productId,
			Long variantId) {
		purchaseOrderDetailsRepository.updateLeftQuantityByPurchaseOrderId(leftQuantity, purchaseOrderId, productId, variantId);		
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.purchase.PurchaseOrderDetailsService#getAllLeftQuantity(java.lang.Long)
	 */
	@Override
	public List<Integer> getAllLeftQuantity(Long purchaseOrderId) {
		return purchaseOrderDetailsRepository.getAllLeftQuantity(purchaseOrderId);
	}

}
