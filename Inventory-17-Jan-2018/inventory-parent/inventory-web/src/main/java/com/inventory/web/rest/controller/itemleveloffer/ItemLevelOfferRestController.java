/**
 * 
 */
package com.inventory.web.rest.controller.itemleveloffer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.common.constants.AppConstants.OFFERSTATUS;
import com.inventory.common.modal.offer.ItemLevelOffer;
import com.inventory.common.service.offer.ItemLevelOfferService;
import com.inventory.common.service.receive.order.InventoryItemService;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.base.SearchResponseVO;
import com.inventory.web.rest.vo.offer.ItemLevelOfferVO;

/**
 * @author ES002
 *
 */
@RestController
@RequestMapping("service/offer")
public class ItemLevelOfferRestController {

	private static final Logger logger = LoggerFactory.getLogger(ItemLevelOfferRestController.class);

	@Autowired
	private ItemLevelOfferService itemLevelOfferService;

	@Autowired
	private InventoryItemService inventoryItemService;

	/**
	 * 
	 * @param productId
	 * @param variantId
	 * @return
	 */
	@RequestMapping(value = "/getInventoryItemQuantity", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<ItemLevelOfferVO>> getInventoryItemQuantity(@RequestParam("productId") Long productId, @RequestParam("variantId") Long variantId) {
		logger.info("Retriving InventoryItemQuantity  : {}");

		ResponseVO<ItemLevelOfferVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<ItemLevelOfferVO>> response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(HttpStatus.OK);

		try {
			ItemLevelOfferVO itemVO = null;
			List<ItemLevelOfferVO> listVO = new ArrayList<>();

			int quantity = inventoryItemService.getItemQuantity(productId, variantId);
			
			if (quantity == 0) {
				message.setMessageId("DETAILS_NOT_FOUND");
				message.setMessageDetail("Details Not Found!");
				response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (quantity != 0) {
				itemVO = new ItemLevelOfferVO();
				itemVO.setProductId(productId);
				itemVO.setVariantId(variantId);
				itemVO.setQuantity(quantity);
				listVO.add(itemVO);
				message.setMessageId("DETAILS_FOUND");
				message.setMessageDetail("Details Found!");
				message.setData(listVO);
				response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(message, HttpStatus.OK);
			} else {
				message.setMessageId("DETAILS_NOT_FOUND");
				message.setMessageDetail("Details Not Found!");
				response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(message, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Details");
			message.setMessageId("EXCEPTION");
			message.setMessageDetail("Exception occured while Retreving Details");
			response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createNewOffer", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<ItemLevelOfferVO>> addNewOffer(@RequestBody ItemLevelOfferVO itemLevelOfferVO) {
		ResponseVO<ItemLevelOfferVO> message = new ResponseVO<ItemLevelOfferVO>();
		ResponseEntity<ResponseVO<ItemLevelOfferVO>> response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(HttpStatus.OK);

		if (itemLevelOfferVO == null) {
			message.setMessageId("BLANK_DATA");
			message.setMessageDetail("Data not found");
			response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(HttpStatus.NOT_FOUND);
		}

		try {
			int givenQuantity  = itemLevelOfferVO.getGivenQuantity();
			int quantity = inventoryItemService.getItemQuantity(itemLevelOfferVO.getProductId(),itemLevelOfferVO.getVariantId());
			if (quantity != 0 && givenQuantity!=0 && itemLevelOfferVO != null) {
				ItemLevelOffer itemLevelOffer = new ItemLevelOffer();
				itemLevelOffer.setOfferName(itemLevelOfferVO.getOfferName());
				itemLevelOffer.setOfferCode(itemLevelOfferVO.getOfferCode());
				itemLevelOffer.setDescription(itemLevelOfferVO.getDescription());
				itemLevelOffer.setOfferStatus(OFFERSTATUS.ONOFFER);
				itemLevelOffer.setDiscountPercentage(itemLevelOfferVO.getDiscountPercentage());
				itemLevelOffer.setQuantity(itemLevelOfferVO.getGivenQuantity());
				itemLevelOffer.setFlatDiscount(itemLevelOfferVO.getFlatDiscount());
				itemLevelOffer.setStartDate(itemLevelOfferVO.getStartDate());
				itemLevelOffer.setEndDate(itemLevelOfferVO.getEndDate());
				itemLevelOffer.setProductId(itemLevelOfferVO.getProductId());
				itemLevelOffer.setVariantId(itemLevelOfferVO.getVariantId());
				
				ItemLevelOffer itemOffer = itemLevelOfferService.saveOfferDetails(itemLevelOffer);
				
				inventoryItemService.updateOfferIdToNull(itemLevelOfferVO.getProductId(), itemLevelOfferVO.getVariantId());
				
				List<Object[]> listData = inventoryItemService.getAllDetails(itemLevelOfferVO.getProductId(), itemLevelOfferVO.getVariantId());
				for(Object[] obj:listData) {
					if(givenQuantity!=0) {
					Long inv_item_id = (Long)obj[0];
					Long productId = (Long)obj[1];
					Long variantId = (Long)obj[2];
					
					System.out.println("Data----> "+inv_item_id+"   "+productId+"   "+variantId);
					inventoryItemService.updateInventoryOfferId(itemOffer.getOfferDetailId(),itemLevelOfferVO.getProductId(), itemLevelOfferVO.getVariantId(),inv_item_id);
					givenQuantity--;
					}
				}
				
				//inventoryItemService.updateInventoryOfferId(itemOffer.getOfferDetailId(),itemLevelOfferVO.getProductId(), itemLevelOfferVO.getVariantId(),totalQuantity);

				message.setMessageId("FOUND");
				message.setMessageDetail("Successfully Done");
				response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(message, HttpStatus.OK);
			} else {
				message.setMessageId("BLANK_DATA");
				message.setMessageDetail("Data not found");
				response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(message, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			logger.error("Exception Occured While Creating Offers");
			e.printStackTrace();
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Offer Create Failed");
			response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	
	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getAllOfferDetails", method = RequestMethod.GET)
	public ResponseEntity<SearchResponseVO<ItemLevelOfferVO>> getAllOfferDetails(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize) {
		logger.info("Fetching All Offer Details");
		SearchResponseVO<ItemLevelOfferVO> message = new SearchResponseVO<>();
		
		ResponseEntity<SearchResponseVO<ItemLevelOfferVO>> response = new ResponseEntity<SearchResponseVO<ItemLevelOfferVO>>(HttpStatus.OK);
		try {
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
			List<ItemLevelOffer> listOffers = itemLevelOfferService.findAll(pageRequest);


			List<ItemLevelOfferVO> listVOs = new ArrayList<>();

			ItemLevelOfferVO offerVO = null;
			if(listOffers!=null) {
				for (ItemLevelOffer offers : listOffers) {
					offerVO = new ItemLevelOfferVO();
					offerVO.setOfferDetailId(offers.getOfferDetailId());
					offerVO.setOfferName(offers.getOfferName());
					offerVO.setOfferCode(offers.getOfferCode());
					offerVO.setDescription(offers.getDescription());
					offerVO.setDiscountPercentage(offers.getDiscountPercentage());
					offerVO.setStartDate(offers.getStartDate());
					offerVO.setEndDate(offers.getEndDate());
					offerVO.setFlatDiscount(offers.getFlatDiscount());
					offerVO.setOfferStatus(offers.getOfferStatus());
					offerVO.setGivenQuantity(offers.getQuantity());
					offerVO.setProductId(offers.getProductId());
					offerVO.setVariantId(offers.getVariantId());
					
					int quantity = inventoryItemService.getItemQuantity(offers.getProductId(),offers.getVariantId());
					
					offerVO.setQuantity(quantity);
					listVOs.add(offerVO);
				}
			}
			message.setMessageId("GET_ALL_OFFER");
			message.setMessageDetail("Get All Offer Details");
			message.setData(listVOs);
			response = new ResponseEntity<SearchResponseVO<ItemLevelOfferVO>>(message, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error occured while gatting all Offer Details", e);
			message.setMessageId("GET_ALL_OFFER_FAILED");
			message.setMessageDetail("Error occured while performing get all Offer ");
			response = new ResponseEntity<SearchResponseVO<ItemLevelOfferVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	/**
	 * 
	 * @param itemLevelOfferVO
	 * @return
	 */
	@RequestMapping(value = "/updateOfferDetails", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<ItemLevelOfferVO>> updateOfferDetails(@RequestBody ItemLevelOfferVO itemLevelOfferVO) {
		ResponseVO<ItemLevelOfferVO> message = new ResponseVO<ItemLevelOfferVO>();
		ResponseEntity<ResponseVO<ItemLevelOfferVO>> response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(HttpStatus.OK);

		if (itemLevelOfferVO == null) {
			message.setMessageId("BLANK_DATA");
			message.setMessageDetail("Data not found");
			response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(HttpStatus.NOT_FOUND);
		}

		try {
			int givenQuantity  = itemLevelOfferVO.getGivenQuantity();
			//int quantity = inventoryItemService.getItemQuantity(itemLevelOfferVO.getProductId(),itemLevelOfferVO.getVariantId());
			
			ItemLevelOffer offer  = itemLevelOfferService.findByOfferId(itemLevelOfferVO.getOfferDetailId());
			if (offer!=null && itemLevelOfferVO != null) {
				offer.setOfferName(itemLevelOfferVO.getOfferName());
				offer.setOfferCode(itemLevelOfferVO.getOfferCode());
				offer.setDescription(itemLevelOfferVO.getDescription());
				offer.setDiscountPercentage(itemLevelOfferVO.getDiscountPercentage());
				offer.setQuantity(itemLevelOfferVO.getGivenQuantity());
				offer.setFlatDiscount(itemLevelOfferVO.getFlatDiscount());
				offer.setStartDate(itemLevelOfferVO.getStartDate());
				offer.setEndDate(itemLevelOfferVO.getEndDate());
				
				ItemLevelOffer itemOffer = itemLevelOfferService.saveOfferDetails(offer);
				
				inventoryItemService.updateOfferIdToNull(itemLevelOfferVO.getProductId(), itemLevelOfferVO.getVariantId());
				
				List<Object[]> listData = inventoryItemService.getAllDetails(itemLevelOfferVO.getProductId(), itemLevelOfferVO.getVariantId());
				
					for(Object[] obj:listData) {
						if(givenQuantity!=0) {
						Long inv_item_id = (Long)obj[0];
						Long productId = (Long)obj[1];
						Long variantId = (Long)obj[2];
						Long offerDetailId = (Long)obj[3];
						
						System.out.println("Data----> "+inv_item_id+"   "+productId+"   "+variantId+"  "+offerDetailId);
						inventoryItemService.updateInventoryOfferId(itemOffer.getOfferDetailId(),itemLevelOfferVO.getProductId(), itemLevelOfferVO.getVariantId(),inv_item_id);
						givenQuantity--;
						}
					
					}
				message.setMessageId("FOUND");
				message.setMessageDetail("Successfully Updated");
				response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(message, HttpStatus.OK);
			} else {
				message.setMessageId("BLANK_DATA");
				message.setMessageDetail("Data not found");
				response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(message, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			logger.error("Exception Occured While Updating Offers");
			e.printStackTrace();
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Offer Update Failed");
			response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	/**
	 * 
	 * @param offerDetailId
	 * @return
	 */
	@RequestMapping(value = "/deleteOffersById/{offerDetailId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseVO<ItemLevelOfferVO>> deleteOffersById(@PathVariable("offerDetailId") Long offerDetailId) {
		logger.info("Fetching & Deleting Offers with id {} ", offerDetailId);

		ResponseVO<ItemLevelOfferVO> message = new ResponseVO<ItemLevelOfferVO>();
		
		ResponseEntity<ResponseVO<ItemLevelOfferVO>> response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(HttpStatus.OK);
		ItemLevelOffer itemLevelOffer = itemLevelOfferService.findByOfferId(offerDetailId);
		if (itemLevelOffer == null) {
			logger.info("Product Not Found With Id {}" + offerDetailId);
			message.setMessageId("PRODUCT_NOT_FOUND");
			message.setMessageDetail("Product Not Found");
			response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (itemLevelOffer != null) {
				itemLevelOfferService.updateItemLevelOfferStatus(OFFERSTATUS.EXPIRED, offerDetailId);
				message.setMessageId("OFFER_FOUND");
				message.setMessageDetail("Deleting Offer with offerDetailId : " + offerDetailId);
				response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(message, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occured while performing Delete Offer operation", e);
			message.setMessageId("DELETE_OFFERS_FAILED");
			message.setMessageDetail("Error occured while deleting Offers with offerDetailId: " + offerDetailId);
			response = new ResponseEntity<ResponseVO<ItemLevelOfferVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

}
