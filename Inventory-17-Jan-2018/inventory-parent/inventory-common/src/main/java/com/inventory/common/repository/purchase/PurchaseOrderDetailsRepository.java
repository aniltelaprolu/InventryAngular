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

import com.inventory.common.modal.purchase.order.PurchaseOrderDetail;

/**
 * @author ES002
 *
 */
@Repository("purchaseOrderDetails")
public interface PurchaseOrderDetailsRepository extends JpaRepository<PurchaseOrderDetail,Long> {
	
	@Query("SELECT pd.leftQuantity from PurchaseOrderDetail pd where pd.purchaseOrder.purchaseOrderId =:purchaseOrderId AND pd.product.productId=:productId AND pd.variant.variantId=:variantId")
	int getAllQuantityDetails(@Param("purchaseOrderId") Long purchaseOrderId,@Param("productId") Long productId,@Param("variantId") Long variantId);
	
	@Modifying
	@Query("UPDATE PurchaseOrderDetail purchaseOrderDetail SET purchaseOrderDetail.leftQuantity=:leftQuantity WHERE purchaseOrderDetail.purchaseOrder.purchaseOrderId =:purchaseOrderId AND purchaseOrderDetail.product.productId=:productId AND purchaseOrderDetail.variant.variantId=:variantId")
	public void updateLeftQuantityByPurchaseOrderId(@Param("leftQuantity") int leftQuantity,@Param("purchaseOrderId") Long purchaseOrderId,@Param("productId") Long productId,@Param("variantId") Long variantId);
	
	@Query("SELECT pd.leftQuantity from PurchaseOrderDetail pd where pd.purchaseOrder.purchaseOrderId =:purchaseOrderId")
	List<Integer> getAllLeftQuantity(@Param("purchaseOrderId") Long purchaseOrderId);
}
