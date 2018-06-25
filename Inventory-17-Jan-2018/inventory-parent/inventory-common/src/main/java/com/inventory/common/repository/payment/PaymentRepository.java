/**
 * 
 */
package com.inventory.common.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.payment.InventoryPayment;

/**
 * @author ES003
 *
 */

@Repository("paymentRepository")
public interface PaymentRepository extends JpaRepository<InventoryPayment, Long> {
	
}
