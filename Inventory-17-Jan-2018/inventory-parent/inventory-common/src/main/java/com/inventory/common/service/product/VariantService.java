package com.inventory.common.service.product;

import java.util.Set;

import org.springframework.data.domain.PageRequest;

import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.product.Variant;

public interface VariantService {
	public Set<Variant> findAll(PageRequest pageRequest);
	public void deleteByVariantId(Long  variantId);
	public Variant findByVariantId(Long  variantId);
	
	void updateVariantStatus(STATUS variantStatus, Long variantId);
}
