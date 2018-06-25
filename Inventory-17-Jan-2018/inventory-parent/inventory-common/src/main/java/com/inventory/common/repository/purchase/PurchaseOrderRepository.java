/**
 * 
 */
package com.inventory.common.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.common.constants.AppConstants.POSTATUS;
import com.inventory.common.modal.purchase.order.PurchaseOrder;

/**
 * @author ES003
 *
 */
@Repository("purchaseOrderRepository")
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {
	
	@Modifying
	@Query("UPDATE PurchaseOrder purchaseOrder set purchaseOrder.poStatus = :status WHERE purchaseOrder.purchaseOrderId = :purchaseOrderId")
	void updatePurchaseOrderStatus(@Param("purchaseOrderId") Long purchaseOrderId,@Param("status") POSTATUS status);
	
	/*@Query("SELECT purchaseOrder.totalQuantity FROM PurchaseOrder purchaseOrder WHERE purchaseOrder.purchaseOrderId = :purchaseOrderId")
	int getTotalQuantity(@Param("purchaseOrderId") Long purchaseOrderId);
	*/
	@Query("SELECT purchaseOrder.poStatus,purchaseOrder.purchaseOrderId FROM PurchaseOrder purchaseOrder WHERE purchaseOrder.invVendor.venderId = :venderId")
	List<Object[]> getPurchaseOrderStatus(@Param("venderId") Long venderId);
	
	@Query("SELECT purchaseOrder.purchaseOrderNo,purchaseOrder.purchaseOrderId FROM PurchaseOrder purchaseOrder WHERE purchaseOrder.poStatus = :poStatus")
	List<Object[]> getPurchaseOrderNumberByStatus(@Param("poStatus") POSTATUS poStatus);
	
	
	
}
