/**
 * 
 */
package com.inventory.common.specification;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.modal.vendor.InvVendor_;

/**
 * @author ES002
 *
 */
public final class InvVendorSpecification {
	private InvVendorSpecification() {}
	 
	  static public Specification<InvVendor> titleOrDescriptionContainsIgnoreCase(Map<String,String>  colValueMap) {
	        return (root, query, cb) -> {
	            return cb.and(
	                    cb.like(cb.lower(root.<String>get(InvVendor_.venId)), getContainsLikePattern(colValueMap.get("venId"))),
	                    cb.like(cb.lower(root.<String>get(InvVendor_.name)), getContainsLikePattern(colValueMap.get("name"))),
	                    cb.like(cb.lower(root.<String>get(InvVendor_.email)), getContainsLikePattern(colValueMap.get("email")))
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
