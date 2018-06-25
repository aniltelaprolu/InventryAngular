/**
 * 
 */
package com.inventory.common.specification;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.Product_;
import com.inventory.common.modal.product.Variant_;

/**
 * @author ES002
 *
 */
public class ProductSpecification {
	private ProductSpecification() {}
	
	static public Specification<Product> titleOrDescriptionContainsIgnoreCase(Map<String,String>  colValueMap) {
        return (root, query, cb) -> {
            return cb.and(
                    cb.like(cb.lower(root.<String>get(Product_.productName)), getContainsLikePattern(colValueMap.get("productName"))),
                    cb.like(cb.lower(root.<String>get(Product_.productCode)), getContainsLikePattern(colValueMap.get("productCode"))),
                    cb.like(cb.lower(root.<String>get(Product_.description)), getContainsLikePattern(colValueMap.get("description"))),
                    cb.like(cb.lower(root.get(Product_.productStatus).as(String.class)), getContainsLikePattern(colValueMap.get("productStatus")))
            );
        };
    }
 
    private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        }
        else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }
}
