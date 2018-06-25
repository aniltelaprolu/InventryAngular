package com.inventory.web.rest.controller.comboboxdata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.common.constants.AppConstants.POSTATUS;
import com.inventory.common.modal.product.Category;
import com.inventory.common.modal.product.MenuGroup;
import com.inventory.common.modal.product.Parameter;
import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.SubCategory;
import com.inventory.common.modal.product.Variant;
import com.inventory.common.modal.purchase.order.PurchaseOrder;
import com.inventory.common.modal.purchase.order.PurchaseOrderDetail;
import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.modal.vendor.VendorAddress;
import com.inventory.common.service.product.CategoryService;
import com.inventory.common.service.product.MenuGroupService;
import com.inventory.common.service.product.ParameterService;
import com.inventory.common.service.product.ProductService;
import com.inventory.common.service.purchase.PurchaseOrderService;
import com.inventory.common.service.vendor.InvVendorService;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.combobox.CategoryComboVO;
import com.inventory.web.rest.vo.combobox.MenuGroupComboVO;
import com.inventory.web.rest.vo.combobox.ParameterComboVO;
import com.inventory.web.rest.vo.combobox.ProductComboVO;
import com.inventory.web.rest.vo.combobox.PurchaseOrderComboVO;
import com.inventory.web.rest.vo.combobox.SubCategoryComboVO;
import com.inventory.web.rest.vo.combobox.VariantComboVO;
import com.inventory.web.rest.vo.combobox.VendorComboVO;
import com.inventory.web.rest.vo.purchase.PurchaseOrderDetailsVO;
import com.inventory.web.rest.vo.purchase.PurchaseOrderVO;


@RestController
@RequestMapping("service/combodata")
public class ComboboxDataRestController {
	private static final Logger logger = LoggerFactory.getLogger(ComboboxDataRestController.class);

	@Autowired
	private InvVendorService vendorService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MenuGroupService menuGroupservice;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ParameterService parameterService;
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;

	/*
	 * Get Vendor Name
	 * 
	 * */
	@RequestMapping(value = "/getVendorName", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<VendorComboVO>> getVendorName() {
		logger.info("Retriving  Vendor name : {}");

		ResponseVO<VendorComboVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<VendorComboVO>> response = new ResponseEntity<ResponseVO<VendorComboVO>>(HttpStatus.OK);

		try {
			List<InvVendor> listVendor = vendorService.getAllVendor();

			if (listVendor != null) {
				VendorComboVO vendorComboVO = null;
				List<VendorComboVO> listCombo = new ArrayList<VendorComboVO>();
				for (InvVendor vendor : listVendor) {
					vendorComboVO = new VendorComboVO();
					vendorComboVO.setVendorId(vendor.getVenderId());
					vendorComboVO.setVendorName(vendor.getName());
					listCombo.add(vendorComboVO);
				}
				message.setMessageId("VENDOR_FOUND");
				message.setMessageDetail("Vendor Details Found!");
				message.setData(listCombo);
				response = new ResponseEntity<ResponseVO<VendorComboVO>>(message, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Vendor Data");
			message.setMessageId("VENDOR_NOT_RETREVED");
			message.setMessageDetail("Exception occured while Retreving Vendor Data");
			response = new ResponseEntity<ResponseVO<VendorComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getParameterName", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<ParameterComboVO>> getParameterName() {
		logger.info("Retriving  Parameter name : {}");

		ResponseVO<ParameterComboVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<ParameterComboVO>> response = new ResponseEntity<ResponseVO<ParameterComboVO>>(HttpStatus.OK);

		try {
			List<Parameter> listParam = parameterService.getAllData();

			if (listParam != null) {
				ParameterComboVO paramComboVO = null;
				List<ParameterComboVO> listCombo = new ArrayList<ParameterComboVO>();
				for (Parameter param : listParam) {
					paramComboVO = new ParameterComboVO();
					paramComboVO.setParameterId(param.getParameterId());
					paramComboVO.setParameterName(param.getParameterName());
					paramComboVO.setParameterOptions(param.getParameterOptions());
					listCombo.add(paramComboVO);
				}
				message.setMessageId("PARAMETER_FOUND");
				message.setMessageDetail("Parameter Details Found!");
				message.setData(listCombo);
				response = new ResponseEntity<ResponseVO<ParameterComboVO>>(message, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Parameter Data");
			message.setMessageId("PARAMETER_NOT_RETREVED");
			message.setMessageDetail("Exception occured while Retreving Parameter Data");
			response = new ResponseEntity<ResponseVO<ParameterComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	
	/**
	 * 
	 * @param parameterId
	 * @return
	 */
	
	@RequestMapping(value = "/getParameterOptionsById", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<ParameterComboVO>> getParameterOptionsById(@RequestParam("parameterId") Long parameterId) {
		logger.info("Retriving  Parameter Options : {}");

		ResponseVO<ParameterComboVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<ParameterComboVO>> response = new ResponseEntity<ResponseVO<ParameterComboVO>>(HttpStatus.OK);
		
		try {
			Parameter param = parameterService.getByParameterId(parameterId);
			
			if (param != null) {
					ParameterComboVO paramComboVO = new ParameterComboVO();
					paramComboVO.setParameterId(param.getParameterId());
					paramComboVO.setParameterName(param.getParameterName());
					paramComboVO.setParameterOptions(param.getParameterOptions());
					List<ParameterComboVO> listCombo = new ArrayList<ParameterComboVO>();
					listCombo.add(paramComboVO);
					
					message.setMessageId("PARAMETER_VALUE_FOUND");
					message.setMessageDetail("Parameter Details Found!");
					message.setData(listCombo);
					response = new ResponseEntity<ResponseVO<ParameterComboVO>>(message, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Parameter Data");
			message.setMessageId("PARAMETER_NOT_RETREVED");
			message.setMessageDetail("Exception occured while Retreving Parameter Data");
			response = new ResponseEntity<ResponseVO<ParameterComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	/**
	 * 
	 * @param vendorId
	 * @return
	 */
	
	@RequestMapping(value = "/getAddressByVendorId", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<VendorComboVO>> getAddressByVendorId(@RequestParam("vendorId") Long vendorId) {
		logger.info("Retriving  Vendor name : {}");

		ResponseVO<VendorComboVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<VendorComboVO>> response = new ResponseEntity<ResponseVO<VendorComboVO>>(message, HttpStatus.OK);;
		try {
			InvVendor vendor = vendorService.findByVendorId(vendorId);
			List<VendorComboVO> listCombo = new ArrayList<VendorComboVO>();	
			if (vendor != null) {
				VendorComboVO vendorComboVO = null;
					vendorComboVO = new VendorComboVO();
					List<VendorAddress> listAddress= vendor.getVenAddress();
					for(VendorAddress vendorAddress:listAddress) {
						if(vendorAddress.getAddressType().equalsIgnoreCase("SHIPPING")) {
							vendorComboVO.setShippingAddress(vendorAddress.getAddress()+","+vendorAddress.getCountry()+","+vendorAddress.getState()+","+vendorAddress.getCity());
						}
						else{
							vendorComboVO.setBillingAddress(vendorAddress.getAddress()+","+vendorAddress.getCountry()+","+vendorAddress.getState()+","+vendorAddress.getCity());
						}
						listCombo.add(vendorComboVO);
					}
					
				}
				message.setMessageId("VENDOR_FOUND");
				message.setData(listCombo);
				message.setMessageDetail("Vendor Address Found!");
				response = new ResponseEntity<ResponseVO<VendorComboVO>>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Vendor Address");
			message.setMessageId("VENDOR_ADDRESS_NOT_RETREVED");
			message.setMessageDetail("Exception occured while Retreving Vendor Address");
			response = new ResponseEntity<ResponseVO<VendorComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	/*
	 * Get ProductName By VendorId
	 * 
	 * */
	@RequestMapping(value = "/getProductName", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<ProductComboVO>> getProductByVendorId(@RequestParam("vendorId") Long vendorId) {
		logger.info("Retriving  Product name : {}");

		ResponseVO<ProductComboVO> message = new ResponseVO<>();
		InvVendor vendor = vendorService.findByVendorId(vendorId);
		
		ResponseEntity<ResponseVO<ProductComboVO>> response = new ResponseEntity<ResponseVO<ProductComboVO>>(HttpStatus.OK);

		if (vendor == null) {
			message.setMessageId("VENDOR_NOT_FOUND");
			message.setMessageDetail("Vendor Details Not Found!");
			response = new ResponseEntity<ResponseVO<ProductComboVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (vendor != null) {
				Set<Product> setProduct = vendor.getProducts();

				if (setProduct != null) {
					ProductComboVO productComboVO=null;
					Set<ProductComboVO> setProductVO=new HashSet<>();
					List<ProductComboVO> listProductVO=new ArrayList<>(setProductVO);
					for (Product product : setProduct) {
						productComboVO = new ProductComboVO();
						productComboVO.setProductId(product.getProductId());
						productComboVO.setProductName(product.getProductName());
						listProductVO.add(productComboVO);
					}
					message.setMessageId("PRODUCT_FOUND");
					message.setMessageDetail("All Product Name Found!");
					message.setData(listProductVO);
					response = new ResponseEntity<ResponseVO<ProductComboVO>>(message, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Product Details");
			message.setMessageId("PRODUCT_NOT_RETREVED");
			message.setMessageDetail("Exception occured while Retreving Product Details");
			response = new ResponseEntity<ResponseVO<ProductComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	/*
	 * Get ProductName
	 * 
	 * */
	@RequestMapping(value = "/getProductNames", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<ProductComboVO>> getProductName() {
		logger.info("Retriving  Product name : {}");

		ResponseVO<ProductComboVO> message = new ResponseVO<>();
		List<Product> listProducts = productService.getAllProduct();
		ResponseEntity<ResponseVO<ProductComboVO>> response = new ResponseEntity<ResponseVO<ProductComboVO>>(HttpStatus.OK);

		if (listProducts == null||listProducts.isEmpty()) {
			message.setMessageId("VENDOR_NOT_FOUND");
			message.setMessageDetail("Vendor Details Not Found!");
			response = new ResponseEntity<ResponseVO<ProductComboVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (listProducts != null) {
				if (listProducts != null) {
					ProductComboVO productComboVO=null;
					Set<ProductComboVO> setProductVO=new LinkedHashSet<>();
					List<ProductComboVO> listProductVO=new ArrayList<>(setProductVO);
					for (Product product : listProducts) {
						productComboVO = new ProductComboVO();
						productComboVO.setProductId(product.getProductId());
						productComboVO.setProductName(product.getProductName());
						listProductVO.add(productComboVO);
					}
					message.setMessageId("PRODUCT_FOUND");
					message.setMessageDetail("Product Details Found!");
					message.setData(listProductVO);
					response = new ResponseEntity<ResponseVO<ProductComboVO>>(message, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Product Details");
			message.setMessageId("PRODUCT_NOT_RETREVED");
			message.setMessageDetail("Exception occured while Retreving Product Details");
			response = new ResponseEntity<ResponseVO<ProductComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	/*
	 * Get Variant Name
	 * */
	@RequestMapping(value = "/getVariantName", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<VariantComboVO>> getVariantByProductId(@RequestParam("productId") Long productId) {
		logger.info("Retriving  Product name : {}");

		ResponseVO<VariantComboVO> message = new ResponseVO<>();
		Product product = productService.findById(productId);
		
		ResponseEntity<ResponseVO<VariantComboVO>> response = new ResponseEntity<ResponseVO<VariantComboVO>>(HttpStatus.OK);

		if (product == null) {
			message.setMessageId("PRODUCT_NOT_FOUND");
			message.setMessageDetail("Product Details Not Found!");
			response = new ResponseEntity<ResponseVO<VariantComboVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (product != null) {
				Set<Variant> setVariant = product.getVariants();
				
				if (setVariant != null) {
					VariantComboVO comboVO=null;
					Set<VariantComboVO> setVariantComVO=new HashSet<>();
					List<VariantComboVO> listVariantComVO=new ArrayList<>(setVariantComVO);
					for(Variant variant:setVariant) {
						comboVO=new VariantComboVO();
						comboVO.setVariantId(variant.getVariantId());
						comboVO.setVariantName(variant.getVariantName());
						listVariantComVO.add(comboVO);
						
					}
					message.setMessageId("VARIANT_FOUND");
					message.setMessageDetail("Variant Details Found!");
					message.setData(listVariantComVO);
					response = new ResponseEntity<ResponseVO<VariantComboVO>>(message, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Variant Data");
			message.setMessageId("VARIANT_NOT_RETREVED");
			message.setMessageDetail("Exception occured while Retreving Variant Data");
			response = new ResponseEntity<ResponseVO<VariantComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	/*
	 * Get MenuGroup Data
	 * 
	 * */
	@RequestMapping(value = "/getMenus", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<MenuGroupComboVO>> getMenuGroupName() {
		logger.info("Retriving  Vendor name : {}");

		ResponseVO<MenuGroupComboVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<MenuGroupComboVO>> response = new ResponseEntity<ResponseVO<MenuGroupComboVO>>(HttpStatus.OK);
		
		try {
			List<MenuGroup> listMenus = menuGroupservice.getAllMenus();

			if (listMenus != null) {
				MenuGroupComboVO menuGroupComboVO=null;
				List<MenuGroupComboVO> listMenuGroupComboVO=new ArrayList<>();
				for (MenuGroup menuGroup : listMenus) {
					menuGroupComboVO = new MenuGroupComboVO();
					menuGroupComboVO.setMenuGroupId(menuGroup.getMenuGroupId());
					menuGroupComboVO.setMenuGroupName(menuGroup.getMenuGroupName());
					listMenuGroupComboVO.add(menuGroupComboVO);
				}
				message.setMessageId("MENU_FOUND");
				message.setMessageDetail("Menu Details Found!");
				message.setData(listMenuGroupComboVO);
				response = new ResponseEntity<ResponseVO<MenuGroupComboVO>>(message, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving MenuGroup Data");
			message.setMessageId("MENUS_NOT_RETREVED");
			message.setMessageDetail("Exception occured while Retreving MenuGroup Data");
			response = new ResponseEntity<ResponseVO<MenuGroupComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	/*
	 * Get Category By Menu Id
	 * 
	 * */
	
	@RequestMapping(value = "/getCategoryName", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<CategoryComboVO>> getCategoryNameByMenuId(@RequestParam("menuGroupId") Long menuGroupId) {
		logger.info("Retriving  Category name : {}");

		ResponseVO<CategoryComboVO> message = new ResponseVO<>();
		MenuGroup maGroup = menuGroupservice.findByMenuGroupId(menuGroupId);
		ResponseEntity<ResponseVO<CategoryComboVO>> response = new ResponseEntity<ResponseVO<CategoryComboVO>>(HttpStatus.OK);
		
		if (maGroup == null) {
			message.setMessageId("MENUS_NOT_FOUND");
			message.setMessageDetail("Menu Details Not Found!");
			response = new ResponseEntity<ResponseVO<CategoryComboVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (maGroup != null) {
				List<Category> listCategory = maGroup.getCategories();

				if (listCategory != null) {
					CategoryComboVO categoryComboVO=null;
					List<CategoryComboVO> listCategoryComboVO=new ArrayList<>();
					for (Category category : listCategory) {
						categoryComboVO=new CategoryComboVO();
						categoryComboVO.setCategoryId(category.getCategoryId());
						categoryComboVO.setCategoryName(category.getCategoryName());
						listCategoryComboVO.add(categoryComboVO);
					}
					message.setMessageId("CATEGORY_FOUND");
					message.setMessageDetail("Category Details Found!");
					message.setData(listCategoryComboVO);
					response = new ResponseEntity<ResponseVO<CategoryComboVO>>(message, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Category Data");
			message.setMessageId("CATEGORY_NOT_RETREVED");
			message.setMessageDetail("Exception occured while Retreving Category Data");
			response = new ResponseEntity<ResponseVO<CategoryComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	/*
	 * Get SubCategory By CategoryId
	 * 
	 * */
	@RequestMapping(value = "/getSubCategoryName", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<SubCategoryComboVO>> getSubCategoryNameByCategoryId(@RequestParam("categoryId") Long categoryId) {
		logger.info("Retriving  SubCategory name : {}");

		ResponseVO<SubCategoryComboVO> message = new ResponseVO<>();
		Category category = categoryService.findByCategoryId(categoryId);

		if (category == null) {
			message.setMessageId("CATEGORY_NOT_FOUND");
			message.setMessageDetail("Category Details Not Found!");
			return new ResponseEntity<ResponseVO<SubCategoryComboVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (category != null) {
				List<SubCategory> listSubCategory = category.getSubCategories();

				if (listSubCategory != null) {
					SubCategoryComboVO subCategoryComboVO = new SubCategoryComboVO();
					List<SubCategoryComboVO> listSubCategoryComboVO = new ArrayList<>();
					for (SubCategory subCategory : listSubCategory) {
						subCategoryComboVO = new SubCategoryComboVO();
						subCategoryComboVO.setSubCategoryId(subCategory.getSubCategoryId());
						subCategoryComboVO.setSubCategoryName(subCategory.getSubCategoryName());
						listSubCategoryComboVO.add(subCategoryComboVO);
					}
					message.setMessageId("CATEGORY_FOUND");
					message.setMessageDetail("Category Details Found!");
					message.setData(listSubCategoryComboVO);
					return new ResponseEntity<ResponseVO<SubCategoryComboVO>>(message, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Category Data");
			message.setMessageId("CATEGORY_NOT_RETREVED");
			message.setMessageDetail("Exception occured while Retreving Category Data");
			return new ResponseEntity<ResponseVO<SubCategoryComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<ResponseVO<SubCategoryComboVO>>(message, HttpStatus.OK);
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllPurchaseOrderNumber", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<PurchaseOrderComboVO>> getAllPurchaseOrderNumber() {
		logger.info("Retriving  Purchase Order Number : {}");

		ResponseVO<PurchaseOrderComboVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<PurchaseOrderComboVO>> response = new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(HttpStatus.OK);
		
		try {
			List<PurchaseOrder> listPurchase = purchaseOrderService.getAll();
			
			if(listPurchase== null || listPurchase.isEmpty()) {
				message.setMessageId("ORDER_NUMBER_NOT_FOUND");
				message.setMessageDetail("Order Details Not Found!");
				return new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (listPurchase != null) {
				PurchaseOrderComboVO purchaseOrderComboVO=null;
				List<PurchaseOrderComboVO> listComboVO=new ArrayList<>();
				for (PurchaseOrder purchaseOrder : listPurchase) {
					if(purchaseOrder.getPoStatus().equals(POSTATUS.PENDING)||purchaseOrder.getPoStatus().equals(POSTATUS.PARTIALLY)) {
					purchaseOrderComboVO = new PurchaseOrderComboVO();
					purchaseOrderComboVO.setPurchaseId(purchaseOrder.getPurchaseOrderId());
					purchaseOrderComboVO.setPurchaseNO(purchaseOrder.getPurchaseOrderNo());
					listComboVO.add(purchaseOrderComboVO);
					}
					message.setMessageId("ORDER_NUMBER_FOUND");
					message.setMessageDetail("Order Number Found!");
					message.setData(listComboVO);
					response = new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(message, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Order Number");
			message.setMessageId("ORDER_NUMBER_FOUND");
			message.setMessageDetail("Exception occured while Retreving Order Number");
			response = new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	/**
	 * 
	 * @param vendorId
	 * @return
	 */
	@RequestMapping(value = "/getAllPurchaseOrderNumberByVendorId", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<PurchaseOrderComboVO>> getAllPurchaseOrderNumberByVendorId(@RequestParam("vendorId") Long vendorId) {
		logger.info("Retriving  Purchase Order Number : {}");

		ResponseVO<PurchaseOrderComboVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<PurchaseOrderComboVO>> response = new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(HttpStatus.OK);
		
		try {
			//List<PurchaseOrder> listPurchase = purchaseOrderService.getAll();
			
			InvVendor vendor = vendorService.findByVendorId(vendorId);
			List<PurchaseOrder> listPurchase =vendor.getPurchaseOrder();
			
			if(listPurchase== null || listPurchase.isEmpty()) {
				message.setMessageId("ORDER_NUMBER_NOT_FOUND");
				message.setMessageDetail("Order Details Not Found!");
				return new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (listPurchase != null) {
				PurchaseOrderComboVO purchaseOrderComboVO=null;
				List<PurchaseOrderComboVO> listComboVO=new ArrayList<>();
				for (PurchaseOrder purchaseOrder : listPurchase) {
					if(purchaseOrder.getPoStatus().equals(POSTATUS.PENDING)||purchaseOrder.getPoStatus().equals(POSTATUS.PARTIALLY)) {
					purchaseOrderComboVO = new PurchaseOrderComboVO();
					purchaseOrderComboVO.setPurchaseId(purchaseOrder.getPurchaseOrderId());
					purchaseOrderComboVO.setPurchaseNO(purchaseOrder.getPurchaseOrderNo());
					listComboVO.add(purchaseOrderComboVO);
					}
					message.setMessageId("ORDER_NUMBER_FOUND");
					message.setMessageDetail("Order Number Found!");
					message.setData(listComboVO);
					response = new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(message, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Order Number");
			message.setMessageId("ORDER_NUMBER_FOUND");
			message.setMessageDetail("Exception occured while Retreving Order Number");
			response = new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	/**
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/getPurchasedetailsByOrderNumber", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<PurchaseOrderVO>> getPurchasedetailsByOrderNumber(@RequestParam("purchaseOrderId") Long purchaseOrderId) {
		logger.info("Retriving  Purchase Order Details : {}");

		ResponseVO<PurchaseOrderVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<PurchaseOrderVO>> response = new ResponseEntity<ResponseVO<PurchaseOrderVO>>(HttpStatus.OK);
		
		try {
			PurchaseOrder purchaseOrder = purchaseOrderService.findByPurchaseOrderId(purchaseOrderId);
			
			if(purchaseOrder== null) {
				message.setMessageId("DETAILS_NOT_FOUND");
				message.setMessageDetail("Purchase Order Details Not Found!");
				response= new ResponseEntity<ResponseVO<PurchaseOrderVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (purchaseOrder != null) {
				PurchaseOrderVO purchaseOrderVO=null;
				List<PurchaseOrderVO> listVO=new ArrayList<>();
					purchaseOrderVO = new PurchaseOrderVO();
					if(purchaseOrder.getPoStatus().equals(POSTATUS.PENDING) || purchaseOrder.getPoStatus().equals(POSTATUS.PARTIALLY) &&purchaseOrder.getPurchaseOrderId()==purchaseOrderId){
					purchaseOrderVO.setVendorId(purchaseOrder.getInvVendor().getVenderId());
					purchaseOrderVO.setVendorName(purchaseOrder.getInvVendor().getName());
					purchaseOrderVO.setPurchaseId(purchaseOrder.getPurchaseOrderId());
					purchaseOrderVO.setPurchaseNO(purchaseOrder.getPurchaseOrderNo());
					purchaseOrderVO.setBillingAddress(purchaseOrder.getBillingAddress());
					purchaseOrderVO.setShippingAddress(purchaseOrder.getShippingAddress());
					purchaseOrderVO.setOrderDate(purchaseOrder.getOrderDate());
					purchaseOrderVO.setRequiredByDate(purchaseOrder.getRequiredByDate());
					
					Set<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrder.getPurchaseOrderDetail();
					PurchaseOrderDetailsVO purchaseOrderDetailsVO = null;
					Set<PurchaseOrderDetailsVO> setDetailsVO = new LinkedHashSet<>();
					for(PurchaseOrderDetail orderDetail:purchaseOrderDetails) {
						purchaseOrderDetailsVO = new PurchaseOrderDetailsVO();
						purchaseOrderDetailsVO.setProductId(orderDetail.getProduct().getProductId());
						purchaseOrderDetailsVO.setProductName(orderDetail.getProduct().getProductName());
						purchaseOrderDetailsVO.setVariantId(orderDetail.getVariant().getVariantId());
						purchaseOrderDetailsVO.setVariantName(orderDetail.getVariant().getVariantName());
						purchaseOrderDetailsVO.setQuantity(orderDetail.getLeftQuantity());
						setDetailsVO.add(purchaseOrderDetailsVO);
					}
					purchaseOrderVO.setListpurchaseDetails(setDetailsVO);
					listVO.add(purchaseOrderVO);
				}
				
				message.setMessageId("ORDER_NUMBER_FOUND");
				message.setMessageDetail("Order Details Found!");
				message.setData(listVO);
				response = new ResponseEntity<ResponseVO<PurchaseOrderVO>>(message, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Order Details");
			message.setMessageId("ORDER_NUMBER_FOUND");
			message.setMessageDetail("Exception occured while Retreving Order Details");
			response = new ResponseEntity<ResponseVO<PurchaseOrderVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	

}
