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

import com.inventory.common.modal.receive.order.InventoryItem;

/**
 * @author ES002
 *
 */
@Repository("inventoryItemRepository")
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long>{
	
	
	@Query("SELECT count(*) FROM InventoryItem inventoryItem WHERE inventoryItem.product.productId = :productId AND inventoryItem.variant.variantId = :variantId")
	int findQuantityByProductIdAndVariantId(@Param("productId") Long productId,@Param("variantId")Long variantId);

	
	@Modifying
	@Query("UPDATE InventoryItem inventoryItem set inventoryItem.itemLevelOffer.offerDetailId = :offerDetailId WHERE inventoryItem.product.productId = :productId AND inventoryItem.variant.variantId= :variantId AND inventoryItem.inventoryItemId=:inventoryItemId")
	void updateOfferId(@Param("offerDetailId") Long offerDetailId,@Param("productId") Long productId,@Param("variantId") Long variantId,@Param("inventoryItemId") Long inventoryItemId);
	
	@Query("SELECT inventoryItem.inventoryItemId,inventoryItem.product.productId,inventoryItem.variant.variantId,inventoryItem.itemLevelOffer.offerDetailId FROM InventoryItem inventoryItem WHERE inventoryItem.product.productId = :productId AND inventoryItem.variant.variantId = :variantId ORDER BY inventoryItem.createdAt DESC")
	List<Object[]> getAllDetailsByProductIdAndVariantId(@Param("productId") Long productId,@Param("variantId")Long variantId);
	
	@Modifying
	@Query("UPDATE InventoryItem inventoryItem set inventoryItem.itemLevelOffer.offerDetailId = null WHERE inventoryItem.product.productId = :productId AND inventoryItem.variant.variantId= :variantId")
	void updateOfferIdToNull(@Param("productId") Long productId,@Param("variantId")Long variantId);
}
