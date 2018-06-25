package com.inventory.common.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.product.Variant;

public interface VariantRepository extends JpaRepository<Variant, Long>,JpaSpecificationExecutor<Variant>{
	void deleteByVariantId(Long variantId);
	Variant findByVariantId(Long variantId);
	
	@Modifying
	@Query("update Variant v set v.status = :status where v.variantId = :variantId")
	void updateVariantStatus(@Param(value="status") STATUS status, @Param(value="variantId")Long variantId);
	
}
