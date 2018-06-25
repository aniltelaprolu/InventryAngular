/**
 * 
 */
package com.inventory.common.service.impl.offer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.constants.AppConstants.OFFERSTATUS;
import com.inventory.common.constants.BaseDAOConstants;
import com.inventory.common.modal.offer.ItemLevelOffer;
import com.inventory.common.repository.offer.ItemLevelOfferRepository;
import com.inventory.common.service.offer.ItemLevelOfferService;

/**
 * @author ES002
 *
 */
@Service("itemLevelOfferService")
public class ItemLevelOfferServiceImpl implements ItemLevelOfferService {
	
	@Autowired
	private ItemLevelOfferRepository itemLevelOfferRepository;

	/* (non-Javadoc)
	 * @see com.inventory.common.service.offer.ItemLevelOfferService#saveOfferDetails(com.inventory.common.modal.offer.ItemLevelOffer)
	 */
	@Override
	public ItemLevelOffer saveOfferDetails(ItemLevelOffer itemLevelOffer) {
		return itemLevelOfferRepository.save(itemLevelOffer);
	}
	
	
	@Override
	public List<ItemLevelOffer> findAll(PageRequest pageRequest) {
		if(pageRequest == null) {
			pageRequest = new PageRequest(0, BaseDAOConstants.DEFAULT_PAGE_SIZE);
		}
		Page<ItemLevelOffer> invOfferPage = itemLevelOfferRepository.findAll(pageRequest);
		return invOfferPage.getContent();
	}


	/* (non-Javadoc)
	 * @see com.inventory.common.service.offer.ItemLevelOfferService#findByOfferId(java.lang.Long)
	 */
	@Override
	public ItemLevelOffer findByOfferId(Long offerDetailId) {
		return itemLevelOfferRepository.getOne(offerDetailId);
	}


	/* (non-Javadoc)
	 * @see com.inventory.common.service.offer.ItemLevelOfferService#updateItemLevelOfferStatus(com.inventory.common.constants.AppConstants.OFFERSTATUS, java.lang.Long)
	 */
	@Transactional
	@Override
	public void updateItemLevelOfferStatus(OFFERSTATUS offerStatus, Long offerDetailId) {
		itemLevelOfferRepository.updateItemLevelOfferStatus(offerStatus, offerDetailId);
	}
}
