package com.inventory.common.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.common.constants.AppConstants.PRODUCTSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.product.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	Product findByProductId(Long productId);

	void deleteByProductId(Long productId);

	Product findByproductName(String productName);

	@Modifying
	@Query("update Product p set p.status = :status where p.productId = :productId")
	void updateProductStatus(@Param(value = "status") PRODUCTSTATUS status, @Param(value = "productId") Long productId);

	@Query("SELECT COUNT(p) FROM Product p WHERE p.status=:status")
	public Long countByStatus(@Param(value = "status") STATUS status);

	@Query("SELECT COUNT(p) FROM Product p WHERE p.productStatus=:productStatus")
	public Long countByProductStatus(@Param(value = "productStatus") PRODUCTSTATUS productStatus);
}
