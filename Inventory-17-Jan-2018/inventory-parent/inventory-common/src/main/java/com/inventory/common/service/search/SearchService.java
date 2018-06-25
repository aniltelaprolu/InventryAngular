/**
 * 
 */
package com.inventory.common.service.search;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;

import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.Variant;
import com.inventory.common.modal.vendor.InvVendor;

/**
 * @author ES002
 *
 */
public interface SearchService {

	/**
	 * @param columnValueMap
	 * @param pageRequest
	 * @return
	 */
	List<InvVendor> filterVendorData(Map<String, String> columnValueMap, PageRequest pageRequest);
	
	List<Product> filterProductData(Map<String,String> columnValue,PageRequest pageRequest);
	
	List<Variant> filterVariantData(Map<String,String> columnValue,PageRequest pageRequest);
	
}


/*public interface SearchService<T> {
    List<T> filterData(Map<String,String> columnValue,PageRequest pageRequest);
}*/