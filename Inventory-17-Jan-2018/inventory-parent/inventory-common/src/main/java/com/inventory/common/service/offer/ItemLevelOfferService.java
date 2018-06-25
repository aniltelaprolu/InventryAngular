/**
 * 
 */
package com.inventory.common.service.offer;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.inventory.common.constants.AppConstants.OFFERSTATUS;
import com.inventory.common.constants.AppConstants.PRODUCTSTATUS;
import com.inventory.common.modal.offer.ItemLevelOffer;

/**
 * @author ES002
 *
 */
public interface ItemLevelOfferService {
	public ItemLevelOffer saveOfferDetails(ItemLevelOffer itemLevelOffer);

	/**
	 * @param pageRequest
	 * @return
	 */
	List<ItemLevelOffer> findAll(PageRequest pageRequest);
	
	public ItemLevelOffer findByOfferId(Long offerDetailId);
	
	void updateItemLevelOfferStatus(OFFERSTATUS offerStatus, Long offerDetailId);
}
