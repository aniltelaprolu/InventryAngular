/**
 * 
 */
package com.inventory.common.repository.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.common.constants.AppConstants.OFFERSTATUS;
import com.inventory.common.modal.offer.ItemLevelOffer;

/**
 * @author ES002
 *
 */
@Repository("itemLevelOfferRepository")
public interface ItemLevelOfferRepository extends JpaRepository<ItemLevelOffer, Long>{
	
	@Modifying
	@Query("update ItemLevelOffer i set i.offerStatus = :offerStatus where i.offerDetailId = :offerDetailId")
	void updateItemLevelOfferStatus(@Param(value="offerStatus") OFFERSTATUS offerStatus, @Param(value="offerDetailId")Long offerDetailId);
}
