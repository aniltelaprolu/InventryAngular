/**
 * 
 */
package com.inventory.common.service.impl.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.inventory.common.constants.BaseDAOConstants;
import com.inventory.common.modal.payment.InventoryPayment;
import com.inventory.common.repository.payment.PaymentRepository;
import com.inventory.common.service.payment.PaymentService;

/**
 * @author ES003
 *
 */

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	/* (non-Javadoc)
	 * @see com.inventory.common.service.payment.PaymentService#savePaymentData(com.inventory.common.modal.payment.InventoryPayment)
	 */
	@Override
	public InventoryPayment savePaymentData(InventoryPayment inventoryPayment) {
		return paymentRepository.save(inventoryPayment);
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.payment.PaymentService#findByInventoryPaymentId(java.lang.Long)
	 */
	@Override
	public InventoryPayment findByInventoryPaymentId(Long inventoryPaymentId) {
		return paymentRepository.getOne(inventoryPaymentId);
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.payment.PaymentService#findAll(org.springframework.data.domain.PageRequest)
	 */
	@Override
	public List<InventoryPayment> findAll(PageRequest pageRequest) {
		if(pageRequest == null) {
			pageRequest = new PageRequest(0, BaseDAOConstants.DEFAULT_PAGE_SIZE);
		}
		Page<InventoryPayment> paymentPage = paymentRepository.findAll(pageRequest);
		return paymentPage.getContent();
	}

}
