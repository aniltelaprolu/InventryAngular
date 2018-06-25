package com.inventory.web.rest.controller.product;

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

import com.inventory.common.constants.AppConstants.PRODUCTSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.product.Category;
import com.inventory.common.modal.product.Items;
import com.inventory.common.modal.product.MenuGroup;
import com.inventory.common.modal.product.Parameter;
import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.SubCategory;
import com.inventory.common.modal.product.Variant;
import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.service.product.CategoryService;
import com.inventory.common.service.product.MenuGroupService;
import com.inventory.common.service.product.ProductService;
import com.inventory.common.service.product.SubCategoryService;
import com.inventory.common.service.vendor.InvVendorService;
import com.inventory.web.exception.custom.ProductNotFoundException;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.base.SearchResponseVO;
import com.inventory.web.rest.vo.product.ItemsVO;
import com.inventory.web.rest.vo.product.ParameterVO;
import com.inventory.web.rest.vo.product.ProductVO;
import com.inventory.web.rest.vo.product.VariantVO;

@RestController
@RequestMapping("service/product")
public class ProductRestController {
	private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
	
	@Autowired
	private ProductService productService;

	@Autowired
	private InvVendorService vendorService;

	@Autowired
	private MenuGroupService menuGroupService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	
	/*private String generateProductCode() {
		long timeSeed = System.nanoTime(); 

        double randSeed = Math.random() * 1000; 

        long midSeed = (long) (timeSeed * randSeed); 
        
        String s = midSeed + "";
        String subStr = s.substring(0, 9);

        Long finalSeed = Long.parseLong(subStr);
        return finalSeed.toString();
	}*/

	@RequestMapping(value = "/addNewProduct", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<ProductVO>> addNewProduct(@RequestBody ProductVO productObj) {
		ResponseVO<ProductVO> message = new ResponseVO<ProductVO>();
		
		ResponseEntity<ResponseVO<ProductVO>> response= new ResponseEntity<ResponseVO<ProductVO>>(HttpStatus.OK);

		if (productObj == null) {
			message.setMessageId("BLANK_PRODUCT");
			message.setMessageDetail("Product can not be blank");
		}
		try {

			// get Vendor By Id
			InvVendor vendor = vendorService.findByVendorId(productObj.getVendorId());

			if (vendor != null) {
				Set<Product> setProducts = vendor.getProducts();

				Product productData = new Product();

				for (Product products : setProducts) {
					if ((products.getProductCode()).equalsIgnoreCase(productObj.getProductCode())) {
						message.setMessageId("PRODUCT_EXSIST");
						message.setMessageDetail("Product already exsist in the system");
						response = new ResponseEntity<ResponseVO<ProductVO>>(message, HttpStatus.CONFLICT);
					}
				}
				MenuGroup menuGroup = menuGroupService.findByMenuGroupId(productObj.getMenuGroupId());
				Category category = categoryService.findByCategoryId(productObj.getCategoryId());
				SubCategory subCategory = subCategoryService.findBySubCategoryId(productObj.getSubCategoryId());
			
				productData.setMenuGroup(menuGroup);
				productData.setCategory(category);
				productData.setSubCategory(subCategory);
				productData.setProductId(productObj.getProductId());
				productData.setProductName(productObj.getProductName());
				//productData.setProductCode(generateProductCode());
				productData.setDescription(productObj.getDescription());
				productData.setProductStatus(PRODUCTSTATUS.AVAILABLE);
				productData.setStatus(STATUS.ACTIVE);
				setProducts.add(productData);
				vendor.setProducts(setProducts);

				productService.saveProductData(productData);
			}
			logger.info("Product Saved Successfully");
			message.setMessageId("PRODUCT_CREATED");
			message.setMessageDetail("Product created sucessfully");
			response = new ResponseEntity<ResponseVO<ProductVO>>(message, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while saving Product");
			message.setMessageId("PRODUCT_DATA_SAVE_FAILED");
			message.setMessageDetail("Exception occured while saving Product Data");
			response = new ResponseEntity<ResponseVO<ProductVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;

	}

	/*
	 * Get all Product Details
	 */

	@RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
	public ResponseEntity<SearchResponseVO<ProductVO>> getAllProduct(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize) {
		logger.info("Fetching All Product Details");

		SearchResponseVO<ProductVO> message = new SearchResponseVO<>();
		
		ResponseEntity<SearchResponseVO<ProductVO>> response = new ResponseEntity<SearchResponseVO<ProductVO>>(HttpStatus.OK);
		try {
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
			List<Product> listProduct = productService.findAll(pageRequest);

			Set<Variant> listVariant = null;
			VariantVO variantVO = null;
			Set<VariantVO> listVariantVO = null;

			List<Items> listItems = null;
			ItemsVO itemsVO = null;
			List<ItemsVO> listItemsVO = null;

			List<Parameter> listParameters = null;
			ParameterVO parameterVO = null;
			List<ParameterVO> listParameterVO = null;

			List<ProductVO> listVOs = new ArrayList<>();

			ProductVO productVO = null;
			if(listProduct!=null) {
				for (Product product : listProduct) {
					productVO = new ProductVO();
					Set<InvVendor> setVendors = product.getVendors();
					for (InvVendor vendor : setVendors) {
						productVO.setVendorName(vendor.getName());
						productVO.setVendorId(vendor.getVenderId());
					}
					productVO.setProductId(product.getProductId());
					productVO.setProductName(product.getProductName());
					productVO.setProductCode(product.getProductCode());
					/*
					 * productVO.setCategory(product.getCategory());
					 * productVO.setSubCategory(product.getSubCategory());
					 * productVO.setMenuGroup(product.getMenuGroup());
					 */
					productVO.setDescription(product.getDescription());
					productVO.setStatus(product.getProductStatus());
	
					listVariant = product.getVariants();
					listVariantVO = new HashSet<>();
					if (listVariant != null) {
						for (Variant variant : listVariant) {
							variantVO = new VariantVO();
							variantVO.setVariantId(variant.getVariantId());
							variantVO.setVariantName(variant.getVariantName());
							variantVO.setQuantity(variant.getQuantity());
							variantVO.setPrice(variant.getPrice());
	
							/*
							 * Getting List of Parameters
							 */
							/*listParameters = variant.getParameters();
							listParameterVO = new ArrayList<>();
							if (listParameters != null) {
								for (Parameter parameter : listParameters) {
									parameterVO = new ParameterVO();
									parameterVO.setParameterId(parameter.getParameterId());
									parameterVO.setColor(parameter.getColor());
									parameterVO.setLength(parameter.getLength());
									parameterVO.setSize(parameter.getSize());
									listParameterVO.add(parameterVO);
								}
							}*/
	
							/*
							 * Getting List of Items
							 * 
							 */
							/*listItems = variant.getItems();
							listItemsVO = new ArrayList<>();
							if (listItems != null) {
								for (Items items : listItems) {
									itemsVO = new ItemsVO();
									itemsVO.setItemId(items.getItemId());
									itemsVO.setStatus(items.getStatus());
									listItemsVO.add(itemsVO);
								}
							}*/
	
							//variantVO.setItems(listItemsVO);
							//variantVO.setParameters(listParameterVO);
							listVariantVO.add(variantVO);
						}
						
					}
					productVO.setCategoryId(product.getCategory().getCategoryId());
					productVO.setMenuGroupId(product.getMenuGroup().getMenuGroupId());
					productVO.setSubCategoryId(product.getSubCategory().getSubCategoryId());
					productVO.setVariants(listVariantVO);
					listVOs.add(productVO);
				}
			}
			message.setMessageId("GET_ALL_PRODUCT");
			message.setMessageDetail("Get All Product Details");
			message.setData(listVOs);
			response = new ResponseEntity<SearchResponseVO<ProductVO>>(message, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error occured while gatting all Product Details", e);
			message.setMessageId("GET_ALL_PRODUCT_FAILED");
			message.setMessageDetail("Error occured while performing get all Product ");
			response = new ResponseEntity<SearchResponseVO<ProductVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}


	/*
	 * Deleting the product
	 * 
	 */

	@RequestMapping(value = "/deleteProductById/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseVO<ProductVO>> deleteProductById(@PathVariable("productId") Long productId) {
		logger.info("Fetching & Deleting Product with id {} ", productId);

		ResponseVO<ProductVO> message = new ResponseVO<ProductVO>();
		
		ResponseEntity<ResponseVO<ProductVO>> response = new ResponseEntity<ResponseVO<ProductVO>>(HttpStatus.OK);
		Product product = productService.findById(productId);
		if (product == null) {
			logger.info("Product Not Found With Id {}" + productId);
			message.setMessageId("PRODUCT_NOT_FOUND");
			message.setMessageDetail("Product Not Found");
			response = new ResponseEntity<ResponseVO<ProductVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (product != null) {
				productService.updateProductStatus(PRODUCTSTATUS.NOTAVAILABLE, productId);
				message.setMessageId("PRODUCT_FOUND");
				message.setMessageDetail("deleted product with product id : " + productId);
				response = new ResponseEntity<ResponseVO<ProductVO>>(message, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occured while performing Delete product operation", e);
			message.setMessageId("DELETE_PRODUCTS_FAILED");
			message.setMessageDetail("Error occured while deleting product with product id : " + productId);
			response = new ResponseEntity<ResponseVO<ProductVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	

	/*
	 * Update Product Details
	 * 
	 */
	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<ProductVO>> updateProductById(@RequestBody ProductVO productVO) {
		logger.info("Updating Product Details Of Product Id{}", productVO.getProductId());

		ResponseVO<ProductVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<ProductVO>> response = new ResponseEntity<ResponseVO<ProductVO>>(message, HttpStatus.OK);
		
		Product currentProduct = productService.findById(productVO.getProductId());
		if (currentProduct == null) {
			logger.info("Product Not Fount With Id {}" + productVO.getProductId());
			message.setMessageId("PRODUCT_NOT_FOUND");
			message.setMessageDetail("Product Not Found");
			response = new ResponseEntity<ResponseVO<ProductVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (currentProduct != null) {
				currentProduct.setProductId(productVO.getProductId());
				currentProduct.setProductName(productVO.getProductName());
				currentProduct.setProductCode(productVO.getProductCode());
				currentProduct.setDescription(productVO.getDescription());
				/*
				
				
				if(setVendors!=null) {
					Set <Long>venderIdSet=new HashSet<>();
					for (InvVendor vendor : setVendors) {
						venderIdSet.add(vendor.getVenderId());
					}
					if(!venderIdSet.contains(productVO.getNewVendorId())) {
					for (InvVendor vendor : setVendors) {
						if (vendor.getVenderId() == productVO.getVendorId()) {
							setVendors.remove(vendor);
						}
						System.out.println(vendor.getVenderId() == productVO.getVendorId());
					}
					setVendors.add(vendorService.findByVendorId(productVO.getNewVendorId()));
					currentProduct.setVendors(setVendors);
					}
				}
				*/
				
				Set<InvVendor> setVendors = currentProduct.getVendors();
				if(setVendors!=null) {
					for (InvVendor vendor : setVendors) {
						if (vendor.getVenderId() == productVO.getVendorId()) {
							setVendors.remove(vendor);
						}
						System.out.println(vendor.getVenderId() == productVO.getVendorId());
					}
				}
				setVendors.add(vendorService.findByVendorId(productVO.getNewVendorId()));
				currentProduct.setVendors(setVendors);
				
				/*MenuGroup currentMenuGroup = currentProduct.getMenuGroup();
				currentMenuGroup = null;
				MenuGroup newMenuGroup = menuGroupService.findByMenuGroupId(productVO.getNewMenuGroupId());
				currentProduct.setMenuGroup(newMenuGroup);
				
				
				Category currentCategory = currentProduct.getCategory();
				currentCategory = null;
				Category newCategory = categoryService.findByCategoryId(productVO.getNewCategoryId());
				currentProduct.setCategory(newCategory);
				

				SubCategory currentSubCategory = currentProduct.getSubCategory();
				currentSubCategory = null;
				SubCategory newSubCategory = subCategoryService.findBySubCategoryId(productVO.getNewSubCategoryId());
				currentProduct.setSubCategory(newSubCategory);*/
				 
			}
			productService.saveProductData(currentProduct);
			message.setMessageId("PRODUCT_FOUND");
			message.setMessageDetail("Product Updated");
			response = new ResponseEntity<ResponseVO<ProductVO>>(message, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Exception Occured While Updating Product");
			e.printStackTrace();
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Product Update Failed");
			response = new ResponseEntity<ResponseVO<ProductVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	/*
	 * Get All Product By Vendor Id
	 * 
	 */

	@SuppressWarnings("unused")
	@RequestMapping(value = "/getProductsByVendorId/{vendorId}", method = RequestMethod.GET)
	public ResponseEntity<SearchResponseVO<List<ProductVO>>> getProductByVendorId(
			@PathVariable("vendorId") Long vendorId) throws ProductNotFoundException{
		logger.info("Getting Product Details Of Vendor By VenodrId{}", vendorId);

		SearchResponseVO<List<ProductVO>> message = new SearchResponseVO<>();
		InvVendor vendor = vendorService.findByVendorId(vendorId);

		if (vendor == null) {
			logger.info("Product Not Found With Vendor Id {}" + vendorId);
			message.setMessageId("PRODUCT_NOT_FOUND");
			message.setMessageDetail("Product Not Found");
			return new ResponseEntity<SearchResponseVO<List<ProductVO>>>(message, HttpStatus.NOT_FOUND);
		}
		try {
			if (vendor != null) {
				Set<Product> setProducts = vendor.getProducts();
				ProductVO productVO = null;
				Set<ProductVO> setProductVO = new HashSet<>();
				List<ProductVO> listProducts = new ArrayList<>(setProductVO);
				for (Product products : setProducts) {
					productVO = new ProductVO();
					productVO.setProductId(products.getProductId());
					productVO.setProductName(products.getProductName());
					productVO.setProductCode(products.getProductCode());
					productVO.setDescription(products.getDescription());
					productVO.setStatus(products.getProductStatus());

					// get Variants
					Set<Variant> setVariants = products.getVariants();
					VariantVO variantVO = null;
					Set<VariantVO> setVariantVO = new HashSet<>();
					for (Variant variants : setVariants) {
						variantVO = new VariantVO();
						variantVO.setVariantId(variants.getVariantId());
						variantVO.setVariantName(variants.getVariantName());
						variantVO.setPrice(variants.getPrice());
						variantVO.setQuantity(variants.getQuantity());

						// get Parameters
						/*List<Parameter> listParameters = variants.getParameters();
						ParameterVO parameterVO = null;
						List<ParameterVO> listParameterVO = new ArrayList<>();
						for (Parameter parameters : listParameters) {
							parameterVO = new ParameterVO();
							parameterVO.setParameterId(parameters.getParameterId());
							parameterVO.setColor(parameters.getColor());
							parameterVO.setLength(parameters.getLength());
							listParameterVO.add(parameterVO);
						}*/

						// get Items
						/*List<Items> listitems = variants.getItems();
						ItemsVO itemsVO = null;
						List<ItemsVO> listItemsVO = new ArrayList<>();
						for (Items items : listitems) {
							itemsVO = new ItemsVO();
							itemsVO.setItemId(items.getItemId());
							itemsVO.setStatus(items.getStatus());
							listItemsVO.add(itemsVO);
						}*/
						setVariantVO.add(variantVO);
					}
					productVO.setVariants(setVariantVO);
					listProducts.add(productVO);
				}
				List<List<ProductVO>> listVos = new ArrayList<>();
				listVos.add(listProducts);
				message.setMessageId("PRODUCT_FOUND");
				message.setMessageDetail("Product Found");
				message.setData(listVos);
				return new ResponseEntity<SearchResponseVO<List<ProductVO>>>(message, HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.error("Exception Occured While Fetching Product");
			e.printStackTrace();
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Product Retrival Failed");
			return new ResponseEntity<SearchResponseVO<List<ProductVO>>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<SearchResponseVO<List<ProductVO>>>(message, HttpStatus.OK);
	}

	
}
