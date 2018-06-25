/**
 * 
 */
package com.inventory.common.service.search;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.Variant;
import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.repository.product.ProductRepository;
import com.inventory.common.repository.product.VariantRepository;
import com.inventory.common.repository.vendor.InvVendorRepository;
import com.inventory.common.specification.InvVendorSpecification;
import com.inventory.common.specification.ProductSpecification;
import com.inventory.common.specification.VariantSpecification;

/**
 * @author ES002
 *
 */
@Service("searchService")
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private InvVendorRepository invVendorRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private VariantRepository variantRepository;

	/* (non-Javadoc)
	 * @see com.inventory.common.service.search.SearchService#filterVendorData(java.util.Map, org.springframework.data.domain.PageRequest)
	 */
	@Override
	public List<InvVendor> filterVendorData(Map<String, String> columnValueMap, PageRequest pageRequest) {
		// TODO Auto-generated method stub
	       Specification<InvVendor> searchSpec = InvVendorSpecification.titleOrDescriptionContainsIgnoreCase(columnValueMap);
	        List<InvVendor> searchResults = invVendorRepository.findAll(searchSpec);
		    return searchResults;
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.search.SearchService#filterProductData(java.util.Map, org.springframework.data.domain.PageRequest)
	 */
	@Override
	public List<Product> filterProductData(Map<String, String> columnValue, PageRequest pageRequest) {
		Specification<Product> searchSpec = ProductSpecification.titleOrDescriptionContainsIgnoreCase(columnValue);
        List<Product> searchResults = productRepository.findAll(searchSpec);
		return searchResults;
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.search.SearchService#filterVariantData(java.util.Map, org.springframework.data.domain.PageRequest)
	 */
	@Override
	public List<Variant> filterVariantData(Map<String, String> columnValue, PageRequest pageRequest) {
		Specification<Variant> searchSpec = VariantSpecification.variantContainsIgnoreCase(columnValue);
        List<Variant> searchResults = variantRepository.findAll(searchSpec);
		return searchResults;
	}

	
}
