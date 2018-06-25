/**
 * 
 */
package com.inventory.common.service.payment;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.inventory.common.modal.payment.InventoryPayment;

/**
 * @author ES003
 *
 */
public interface PaymentService {
	public InventoryPayment savePaymentData(InventoryPayment inventoryPayment);
	public InventoryPayment findByInventoryPaymentId(Long inventoryPaymentId);
	/**
	 * @param pageRequest
	 * @return
	 */
	public List<InventoryPayment> findAll(PageRequest pageRequest);
}
