/**
 * 
 */
package com.inventory.common.repository.receive.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.receive.order.ReceivedOrder;

/**
 * @author ES003
 *
 */
@Repository("receivedOrderRepository")
public interface ReceivedOrderRepository extends JpaRepository<ReceivedOrder,Long> {
	
	/*@Query("SELECT receivedOrder.totalQuantity FROM ReceivedOrder receivedOrder WHERE receivedOrder.receivedOrderId = :receivedOrderId")
	int getTotalQuantity(@Param("receivedOrderId") Long receivedOrderId);*/
	
	
	@Query("SELECT receivedOrder.receivedOrderId,receivedOrder.receviedOrderNo FROM ReceivedOrder receivedOrder WHERE receivedOrder.purchaseOrder.purchaseOrderId = :purchaseOrderId")
	List<Object[]> getReceivedOrderNumber(@Param("purchaseOrderId") Long purchaseOrderId);
	
	@Modifying
	@Query("UPDATE ReceivedOrder receivedOrder SET receivedOrder.dueAmount=:dueAmount WHERE receivedOrder.receivedOrderId = :receivedOrderId")
	public void updateDueAmountByReceivedOrderId(@Param("dueAmount") Double dueAmount,@Param("receivedOrderId") Long receivedOrderId);
	
	@Query("SELECT r.dueAmount FROM ReceivedOrder r where r.receivedOrderId = :receivedOrderId")
	public Double getTotalDueAmount(@Param("receivedOrderId") Long receivedOrderId);
	
}
