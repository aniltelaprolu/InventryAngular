/**
 * 
 */
package com.inventory.common.repository.receive.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.receive.order.ReceivedOrderDetail;

/**
 * @author ES003
 *
 */
@Repository("receivedOrderDetailsRepository")
public interface ReceivedOrderDetailsRepository extends JpaRepository<ReceivedOrderDetail,Long> {
	
	@Query("SELECT rd.product.productId, rd.variant.variantId, rd.price, rd.quantity from ReceivedOrderDetail rd where rd.receivedOrder.receivedOrderId =:receivedOrderId")
	List<Object[]> getOrderDetails(@Param("receivedOrderId") Long receivedOrderId);
	
	@Query("SELECT rd.price, rd.quantity from ReceivedOrderDetail rd where rd.receivedOrder.receivedOrderId =:receivedOrderId")
	List<Object[]> getTotalAmountDetails(@Param("receivedOrderId") Long receivedOrderId);
	
}
