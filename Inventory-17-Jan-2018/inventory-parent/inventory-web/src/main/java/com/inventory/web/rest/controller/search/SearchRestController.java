/**
 * 
 */
package com.inventory.web.rest.controller.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.Variant;
import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.modal.vendor.VendorAddress;
import com.inventory.common.modal.vendor.VendorPhone;
import com.inventory.common.service.search.SearchService;
import com.inventory.web.rest.vo.base.SearchResponseVO;
import com.inventory.web.rest.vo.product.ProductVO;
import com.inventory.web.rest.vo.product.VariantVO;
import com.inventory.web.rest.vo.vendor.InvVendorVO;
import com.inventory.web.rest.vo.vendor.VendorAddressVO;
import com.inventory.web.rest.vo.vendor.VendorPhoneVO;

/**
 * @author ES002
 *
 */
@RestController
@RequestMapping("service/search")
public class SearchRestController {
	private static final Logger logger = LoggerFactory.getLogger(SearchRestController.class);

	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/searchData", method = RequestMethod.GET)
	public ResponseEntity<?> searchDataByModule(/* @RequestBody PageResultVO pageResultVO */) {

		PageResultVO pageResultVO = new PageResultVO();
		Map<String, String> columnValue = new HashMap<>();
		columnValue.put("venId", "INTN001");
		pageResultVO.setColumnValue(columnValue);
		pageResultVO.setModuleName("vendor");
		pageResultVO.setPageNo(2);
		pageResultVO.setPageSize(5);

		Map<String, String> columnValue1 = new HashMap<>();
		columnValue1.put("variantName", "Round Nake");
		pageResultVO.setColumnValue(columnValue1);
		pageResultVO.setModuleName("variant");
		pageResultVO.setPageNo(2);
		pageResultVO.setPageSize(5);

		/*
		 
		  Map<String,String> columnValue = new HashMap<>();
		  columnValue.put("variantName", "Round Nake");
		  pageResultVO.setColumnValue(columnValue);
		  pageResultVO.setModuleName("variant"); pageResultVO.setPageNo(2);
		  pageResultVO.setPageSize(5);
		 
		 */
		ResponseEntity<?> returnedValue = new ResponseEntity<String>(HttpStatus.OK);
		try {
			String moduleName = pageResultVO.getModuleName();
			Map<String, String> columnValueMap = pageResultVO.getColumnValue();
			PageRequest pageRequest = new PageRequest(pageResultVO.getPageNo() - 1, pageResultVO.getPageSize());

			switch (moduleName) {
				case "vendor": {
					List<InvVendor> listVendor = searchService.filterVendorData(columnValueMap, pageRequest);
					SearchResponseVO<InvVendorVO> message = new SearchResponseVO<>();
	
					List<InvVendorVO> invVendorVOs = new ArrayList<>();
					InvVendorVO vendorVO = null;
					List<VendorAddress> listAddrs = null;
					VendorAddressVO addrsVO = null;
					List<VendorAddressVO> listAddrsVO = null;
	
					Set<VendorPhone> listPhones = null;
					VendorPhoneVO phoneVO = null;
					Set<VendorPhoneVO> listPhVO = null;
					if (listVendor != null) {
						for (InvVendor vendor : listVendor) {
							vendorVO = new InvVendorVO();
							vendorVO.setEmail(vendor.getEmail());
							vendorVO.setName(vendor.getName());
							vendorVO.setVendorId(vendor.getVenderId());
							vendorVO.setVenId(vendor.getVenId());
							listAddrs = vendor.getVenAddress();
							listAddrsVO = new ArrayList<>();
							if (listAddrs != null) {
								for (VendorAddress addrs : listAddrs) {
									addrsVO = new VendorAddressVO();
									addrsVO.setAddressId(addrs.getAddressId());
									addrsVO.setAddress(addrs.getAddress());
									addrsVO.setAddressType(addrs.getAddressType());
									addrsVO.setCity(addrs.getCity());
									addrsVO.setCountry(addrs.getCountry());
									addrsVO.setPincode(addrs.getPincode());
									addrsVO.setState(addrs.getState());
									listAddrsVO.add(addrsVO);
								}
							}
							listPhones = vendor.getPhones();
							listPhVO = new HashSet<>();
							if (listPhones != null) {
								for (VendorPhone phones : listPhones) {
									phoneVO = new VendorPhoneVO();
									phoneVO.setMobilePhonNo(phones.getMobilePhonNo());
									phoneVO.setOffice1phonNo(phones.getOffice1phonNo());
									phoneVO.setOffice2phonNo(phones.getOffice2phonNo());
									listPhVO.add(phoneVO);
								}
							}
							vendorVO.setVenAddress(listAddrsVO);
							vendorVO.setVenPhones(listPhVO);
							invVendorVOs.add(vendorVO);
						}
					} else {
						message.setMessageId("VENDOR_NOT_FOUND");
						message.setMessageDetail("Vendor Details Not Found");
						returnedValue = new ResponseEntity<SearchResponseVO<InvVendorVO>>(message, HttpStatus.NOT_FOUND);
					}
	
					message.setMessageId("ALL_VENDOR_FOUND");
					message.setMessageDetail("All Vendor Details Found!");
					message.setData(invVendorVOs);
					returnedValue = new ResponseEntity<SearchResponseVO<InvVendorVO>>(message, HttpStatus.OK);
	
				}
				case "product": {
					List<Product> listProduct = searchService.filterProductData(columnValueMap, pageRequest);
					SearchResponseVO<ProductVO> message = new SearchResponseVO<>();
	
					List<ProductVO> listVOs = new ArrayList<>();
	
					ProductVO productVO = null;
					if (listProduct != null) {
						for (Product product : listProduct) {
							productVO = new ProductVO();
							productVO.setProductId(product.getProductId());
							productVO.setProductName(product.getProductName());
							productVO.setProductCode(product.getProductCode());
							productVO.setDescription(product.getDescription());
							productVO.setStatus(product.getProductStatus());
	
							listVOs.add(productVO);
							message.setMessageId("GET_ALL_PRODUCT");
							message.setMessageDetail("Get All Product Details");
							message.setData(listVOs);
							return new ResponseEntity<SearchResponseVO<ProductVO>>(message, HttpStatus.OK);
						}
					} else {
						message.setMessageId("PRODUCT_NOT_FOUND");
						message.setMessageDetail("Product Details Detail Not Found!");
						return new ResponseEntity<SearchResponseVO<ProductVO>>(message, HttpStatus.NOT_FOUND);
					}
				}
				case "variant": {
					List<Variant> listVarinat = searchService.filterVariantData(columnValueMap, pageRequest);
					SearchResponseVO<VariantVO> message = new SearchResponseVO<>();
	
					List<VariantVO> listVOs = new ArrayList<>();
	
					VariantVO variantVO = null;
					if (listVarinat != null) {
						for (Variant variant : listVarinat) {
							variantVO = new VariantVO();
							variantVO.setVariantId(variant.getVariantId());
							variantVO.setVariantName(variant.getVariantName());
							variantVO.setPrice(variant.getPrice());
	
							listVOs.add(variantVO);
							message.setMessageId("GET_VARIANT");
							message.setMessageDetail("All Variant Details Found");
							message.setData(listVOs);
							return new ResponseEntity<SearchResponseVO<VariantVO>>(message, HttpStatus.OK);
						}
					} else {
						message.setMessageId("VARIANT_NOT_FOUND");
						message.setMessageDetail("Variant Details Not Found");
						return new ResponseEntity<SearchResponseVO<VariantVO>>(message, HttpStatus.NOT_FOUND);
					}
				}
				default: {
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while saving Product");
			return new ResponseEntity<SearchResponseVO<?>>(HttpStatus.EXPECTATION_FAILED);
		}
		return returnedValue;
	}
}
