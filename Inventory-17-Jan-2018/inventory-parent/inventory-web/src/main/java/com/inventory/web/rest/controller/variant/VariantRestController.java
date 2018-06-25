/**
 * 
 */
package com.inventory.web.rest.controller.variant;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.multipart.MultipartFile;

import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.product.Items;
import com.inventory.common.modal.product.Parameter;
import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.Variant;
import com.inventory.common.modal.product.VariantImage;
import com.inventory.common.modal.product.VariantParameter;
import com.inventory.common.service.product.ParameterService;
import com.inventory.common.service.product.ProductService;
import com.inventory.common.service.product.VariantService;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.base.SearchResponseVO;
import com.inventory.web.rest.vo.product.ItemsVO;
import com.inventory.web.rest.vo.product.ParameterVO;
import com.inventory.web.rest.vo.product.VariantParameterVO;
import com.inventory.web.rest.vo.product.VariantVO;

/**
 * @author ES002
 *
 */
@RestController
@RequestMapping("service/product")
public class VariantRestController {
	private static final Logger logger = LoggerFactory.getLogger(VariantRestController.class);
	private static String uploadedFolder = "classpath:\\resources\\static\\variantImage\\";

	@Autowired
	private ProductService productService;

	@Autowired
	private VariantService variantService;
	
	@Autowired
	private ParameterService parameterService;
	

	/*
	 * Get All Variants
	 * 
	 */
	@RequestMapping(value = "/getAllVariants", method = RequestMethod.GET)
	public ResponseEntity<SearchResponseVO<VariantVO>> getAllVariants(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize) {
		logger.info("Fetching All Variant Details");

		SearchResponseVO<VariantVO> message = new SearchResponseVO<>();
		
		ResponseEntity<SearchResponseVO<VariantVO>> response = new ResponseEntity<SearchResponseVO<VariantVO>>(message, HttpStatus.OK);
		try {
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
			Set<Variant> setVariants = variantService.findAll(pageRequest);

			Set<VariantVO> setVariantVO = new HashSet<>();
			List<VariantVO> listVariantVO = new ArrayList<>(setVariantVO);

			VariantVO variantVO = null;
			if(setVariants!=null) {
				for (Variant variant : setVariants) {
					if(variant.getStatus().equals(STATUS.ACTIVE)) {
						variantVO = new VariantVO();
						Product product = variant.getProducts();
						variantVO.setProductId(product.getProductId());
						variantVO.setProductName(product.getProductName());
						variantVO.setVariantId(variant.getVariantId());
						variantVO.setVariantName(variant.getVariantName());
						variantVO.setQuantity(variant.getQuantity());
						variantVO.setPrice(variant.getPrice());
						
						List<VariantParameter> listVariantParameter = variant.getVariantParameters();
						VariantParameterVO variantParameterVO = null;
						List<VariantParameterVO> listVariantParameterVO = new ArrayList<>();
						if(listVariantParameter!=null) {
							for(VariantParameter variantParameter : listVariantParameter) {
							variantParameterVO = new VariantParameterVO();
							variantParameterVO.setVariantParameterId(variantParameter.getVariantParameterId());
							variantParameterVO.setParameterValue(variantParameter.getValue());
							variantParameterVO.setParameterStatus(variantParameter.getStatus());
							listVariantParameterVO.add(variantParameterVO);
							}
						}
						variantVO.setVariantParams(listVariantParameterVO);
						listVariantVO.add(variantVO);
					}
				}
				message.setMessageId("GET_ALL_VARIANT");
				message.setMessageDetail("Get All Variant Details");
				message.setData(listVariantVO);
				response = new ResponseEntity<SearchResponseVO<VariantVO>>(message, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error occured while gatting all Variant Details", e);
			e.printStackTrace();
			message.setMessageId("GET_ALL_VARIANT_FAILED");
			message.setMessageDetail("Error occured while performing get all Variant ");
			response = new ResponseEntity<SearchResponseVO<VariantVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	/*
	 * 
	 * Adding new Variant to existing product
	 *
	 */
	@RequestMapping(value = "/addNewVariant", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<VariantVO>> addNewVariant(@RequestBody VariantVO variantVO) {
		logger.info("Adding New Variant to existing Product{}", variantVO.getProductId());

		ResponseVO<VariantVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<VariantVO>> response = new ResponseEntity<ResponseVO<VariantVO>>(message, HttpStatus.OK);

		try {
			List<MultipartFile> files = variantVO.getMultipartFiles();
			List<VariantImage> variantimages = new ArrayList<>();
			if (files != null) {
				for (MultipartFile file : files) {
					VariantImage variantImage = new VariantImage();
					variantImage.setImageName(file.getOriginalFilename());
					variantImage.setImagePath(uploadedFolder + file.getOriginalFilename());
					variantimages.add(variantImage);
				}
			}

			Product currentProduct = productService.findById(variantVO.getProductId());

			if (currentProduct == null) {
				logger.info("Product Not Found With Id {}" + variantVO.getProductId());
				message.setMessageId("PRODUCT_NOT_FOUND");
				message.setMessageDetail("Product Not Found");
				response = new ResponseEntity<ResponseVO<VariantVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (currentProduct != null) {

				Set<Variant> listVariant = currentProduct.getVariants();

				Variant variant = new Variant();
				variant.setVariantName(variantVO.getVariantName());
				variant.setQuantity(variantVO.getQuantity());
				variant.setPrice(variantVO.getPrice());
				variant.setStatus(STATUS.ACTIVE);

				
				List<VariantParameterVO> listParamVO = variantVO.getVariantParams();
				VariantParameter variantParameter = null;
				List<VariantParameter> listparam = new ArrayList<>();
				for(VariantParameterVO param : listParamVO) {
					variantParameter = new VariantParameter();
					Parameter parameter = parameterService.getByParameterId(param.getParameterId());
					variantParameter.setParameter(parameter);
					variantParameter.setStatus(STATUS.ACTIVE);
					variantParameter.setValue(param.getParameterValue());
					listparam.add(variantParameter);
				}
				
				variant.setVariantParameters(listparam);
				variant.setVariantImages(variantimages);

				listVariant.add(variant);
				currentProduct.setVariants(listVariant);

				Product product = productService.saveProductData(currentProduct);
				
				Set<Variant> variants = product.getVariants();
				Long varId = 0l;
				for (Variant v : variants) {
					varId = v.getVariantId();
				}
				if (files != null) {
					for (MultipartFile file : files) {
						file.transferTo(new File(uploadedFolder + varId + file.getOriginalFilename()));
					}
				}

				message.setMessageId("PRODUCT_FOUND");
				message.setMessageDetail("New Variant Added");
				response = new ResponseEntity<ResponseVO<VariantVO>>(message, HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.error("Exception Occured While Adding New Variant");
			e.printStackTrace();
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("New Variant Add Failed");
			response = new ResponseEntity<ResponseVO<VariantVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	/*
	 * Update Variant Details
	 * 
	 */
	@RequestMapping(value = "/updateVariant", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<VariantVO>> updateVariantById(@RequestBody VariantVO variantVO) {
		logger.info("Updating Variant Details Of Product Id{}", variantVO.getProductId());

		ResponseVO<VariantVO> message = new ResponseVO<>();
		
		ResponseEntity<ResponseVO<VariantVO>> response = new ResponseEntity<ResponseVO<VariantVO>>(message, HttpStatus.OK);
		
		Product currentProduct = productService.findById(variantVO.getProductId());
		if (currentProduct == null) {
			logger.info("Product Not Fount With Id {}" + variantVO.getProductId());
			message.setMessageId("PRODUCT_NOT_FOUND");
			message.setMessageDetail("Product Not Found");
			response = new ResponseEntity<ResponseVO<VariantVO>>(message, HttpStatus.NOT_FOUND);
		}
		try {
			if (currentProduct != null) {
				Set<Variant> setVariants = currentProduct.getVariants();

				for (Variant variant : setVariants) {
					if (variant.getVariantId() == variantVO.getVariantId()) {
						variant.setVariantId(variantVO.getVariantId());
						variant.setPrice(variantVO.getPrice());
						variant.setQuantity(variantVO.getQuantity());
						variant.setVariantName(variantVO.getVariantName());

						List<VariantParameterVO> listVariantParameterVO = variantVO.getVariantParams();
						
						List<VariantParameter> listVariantParameter = variant.getVariantParameters();
						
						if(listVariantParameter!=null) {
							for(VariantParameter variantParameter : listVariantParameter) {
								if(listVariantParameterVO!=null) {
									for(VariantParameterVO variantParameterVO:listVariantParameterVO) {
										if(variantParameter.getVariantParameterId()==variantParameterVO.getVariantParameterId()) {
											variantParameter.setVariantParameterId(variantParameterVO.getVariantParameterId());
											variantParameter.setValue(variantParameterVO.getParameterValue());
											listVariantParameter.add(variantParameter);
										}
									}
								}
							}
						}
						variant.setVariantParameters(listVariantParameter);
					}
					setVariants.add(variant);
				}
				currentProduct.setVariants(setVariants);
			}

			productService.saveProductData(currentProduct);
			message.setMessageId("PRODUCT_FOUND");
			message.setMessageDetail("Variant Updated");
			response = new ResponseEntity<ResponseVO<VariantVO>>(message, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Exception Occured While Updating Variant");
			e.printStackTrace();
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Variant Update Failed");
			response = new ResponseEntity<ResponseVO<VariantVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	/*
	 * 
	 * Get List Of Variant Based On ProductId
	 * 
	 */

	@SuppressWarnings("unused")
	@RequestMapping(value = "/getVariantsByProductId/{productId}", method = RequestMethod.POST)
	public ResponseEntity<SearchResponseVO<VariantVO>> getVariantsByProductId(
			@PathVariable("productId") Long productId) {
		logger.info("Getting Variant Details Of Product Id{}", productId);

		SearchResponseVO<VariantVO> message = new SearchResponseVO<>();

		Product currentProduct = productService.findById(productId);
		if (currentProduct == null) {
			logger.info("Product Not Fount With Id {}" + productId);
			message.setMessageId("PRODUCT_NOT_FOUND");
			message.setMessageDetail("Product Not Found");
			return new ResponseEntity<SearchResponseVO<VariantVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (currentProduct != null) {
				Set<Variant> listVariant = null;
				listVariant = currentProduct.getVariants();
				VariantVO variantVO = null;
				Set<VariantVO> listVariantVO = new HashSet<>();

				List<Parameter> listParameter = null;
				ParameterVO parameterVO = null;
				List<ParameterVO> listParameterVO = null;
				

				List<VariantVO> listVOs = new ArrayList<>(listVariantVO);

				List<Items> listItems = null;
				ItemsVO itemsVO = null;
				List<ItemsVO> listItemsVO = null;
				if(listVariant!=null) {
					for (Variant variant : listVariant) {
						variantVO = new VariantVO();
						variantVO.setProductId(productId);
						variantVO.setVariantId(variant.getVariantId());
						variantVO.setVariantName(variant.getVariantName());
						variantVO.setPrice(variant.getPrice());
						variantVO.setQuantity(variant.getQuantity());
	
						/*listParameter = variant.getParameters();
						listParameterVO = new ArrayList<>();
						if(listParameter!=null) {
							for (Parameter parameter : listParameter) {
								parameterVO = new ParameterVO();
								parameterVO.setParameterId(parameter.getParameterId());
								parameterVO.setColor(parameter.getColor());
								parameterVO.setLength(parameter.getLength());
								parameterVO.setSize(parameter.getSize());
								listParameterVO.add(parameterVO);
							}
						}*/
	
						/*listItems = variant.getItems();
						listItemsVO = new ArrayList<>();
						if(listItems!=null) {
							for (Items items : listItems) {
								itemsVO = new ItemsVO();
								itemsVO.setItemId(items.getItemId());
								itemsVO.setStatus(items.getStatus());
								listItemsVO.add(itemsVO);
							}
						}
						*/
						
						//variantVO.setParameters(listParameterVO);
						//variantVO.setItems(listItemsVO);
						listVOs.add(variantVO);
					}
				}
				message.setMessageId("VARIANT_FOUND");
				message.setMessageDetail("Variant Found");
				message.setData(listVOs);
				return new ResponseEntity<SearchResponseVO<VariantVO>>(message, HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.error("Exception Occured While Fetching Variant");
			e.printStackTrace();
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Variant Retrival Failed");
			return new ResponseEntity<SearchResponseVO<VariantVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<SearchResponseVO<VariantVO>>(message, HttpStatus.OK);
	}

	
	/**
	 * 
	 * @param variantId
	 * @return
	 */
	@RequestMapping(value = "/deleteVariantById/{variantId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseVO<VariantVO>> deleteVariantById(@PathVariable("variantId") Long variantId) {
		logger.info("Fetching & Deleting Variant with id {} ", variantId);

		ResponseVO<VariantVO> message = new ResponseVO<VariantVO>();
		
		ResponseEntity<ResponseVO<VariantVO>> response = new ResponseEntity<ResponseVO<VariantVO>>(message, HttpStatus.OK);
		
		Variant variant = variantService.findByVariantId(variantId);
		if (variant == null) {
			logger.info("Variant Not Found With Id {}" + variantId);
			message.setMessageId("VARIANT_NOT_FOUND");
			message.setMessageDetail("Variant Not Found");
			response = new ResponseEntity<ResponseVO<VariantVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (variant != null) {
				variantService.updateVariantStatus(STATUS.INACTIVE, variantId);
				message.setMessageId("VARIANT_FOUND");
				message.setMessageDetail("deleted Variant with  id : " + variantId);
				response = new ResponseEntity<ResponseVO<VariantVO>>(message, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occured while performing Delete Variant operation", e);
			message.setMessageId("DELETE_VARIANT_FAILED");
			message.setMessageDetail("Error occured while deleting Variant with id : " + variantId);
			response = new ResponseEntity<ResponseVO<VariantVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

}

