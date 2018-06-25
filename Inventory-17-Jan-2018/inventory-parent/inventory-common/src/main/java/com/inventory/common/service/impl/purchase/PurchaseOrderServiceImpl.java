/**
 * 
 */
package com.inventory.common.service.impl.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.constants.BaseDAOConstants;
import com.inventory.common.constants.AppConstants.POSTATUS;
import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.purchase.order.PurchaseOrder;
import com.inventory.common.repository.purchase.PurchaseOrderRepository;
import com.inventory.common.service.purchase.PurchaseOrderService;

/**
 * @author ES003
 *
 */
@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	/* (non-Javadoc)
	 * @see com.inventory.common.service.purchase.PurchaseOrderService#savepurchaseData(com.inventory.common.modal.purchase.order.PurchaseOrder)
	 */
	@Override
	public PurchaseOrder savepurchaseData(PurchaseOrder purchaseOrder) {
		PurchaseOrder purchase	= purchaseOrderRepository.save(purchaseOrder);
		return purchase;
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.purchase.PurchaseOrderService#getAll()
	 */
	@Override
	public List<PurchaseOrder> getAll() {
		return purchaseOrderRepository.findAll();
	}

	@Override
	public PurchaseOrder findByPurchaseOrderId(Long purchaseOrderId) {
		return purchaseOrderRepository.getOne(purchaseOrderId);
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.purchase.PurchaseOrderService#updatePurchaseOrderStatus(java.lang.Long, com.inventory.common.constants.AppConstants.POSTATUS)
	 */
	@Transactional
	@Override
	public void updatePurchaseOrderStatus(Long purchaseOrderId, POSTATUS status) {
		purchaseOrderRepository.updatePurchaseOrderStatus(purchaseOrderId, status);
	}


	/* (non-Javadoc)
	 * @see com.inventory.common.service.purchase.PurchaseOrderService#getPurchaseOrderStatus(java.lang.Long)
	 */
	@Override
	public List<Object[]> getPurchaseOrderStatus(Long vendorId) {
		return purchaseOrderRepository.getPurchaseOrderStatus(vendorId);
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.purchase.PurchaseOrderService#getPurchaseOrderNoByStatus(java.lang.String)
	 */
	@Override
	public List<Object[]> getPurchaseOrderNoByStatus(POSTATUS poStatus) {
		return purchaseOrderRepository.getPurchaseOrderNumberByStatus(poStatus);
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.purchase.PurchaseOrderService#findAll(org.springframework.data.domain.PageRequest)
	 */
	@Override
	public List<PurchaseOrder> findAll(PageRequest pageRequest) {
		if(pageRequest == null) {
			pageRequest = new PageRequest(0, BaseDAOConstants.DEFAULT_PAGE_SIZE);
		}
		Page<PurchaseOrder> invPurchasetPage = purchaseOrderRepository.findAll(pageRequest);
		return invPurchasetPage.getContent();
	}

	
}
