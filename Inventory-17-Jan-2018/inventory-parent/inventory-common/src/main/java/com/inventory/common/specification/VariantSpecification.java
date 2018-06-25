/**
 * 
 */
package com.inventory.common.specification;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.inventory.common.modal.product.Variant;
import com.inventory.common.modal.product.Variant_;

/**
 * @author ES002
 *
 */
public class VariantSpecification {
private VariantSpecification() {}
	
	static public Specification<Variant> variantContainsIgnoreCase(Map<String,String>  colValueMap) {
        return (root, query, cb) -> {
            return cb.and(
                    cb.like(cb.lower(root.<String>get(Variant_.variantName)), getContainsLikePattern(colValueMap.get("variantName"))),
                    cb.like(cb.lower(root.get(Variant_.price).as(String.class)), getContainsLikePattern(colValueMap.get("price"))),
                    cb.like(cb.lower(root.get(Variant_.quantity).as(String.class)), getContainsLikePattern(colValueMap.get("quantity")))
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
