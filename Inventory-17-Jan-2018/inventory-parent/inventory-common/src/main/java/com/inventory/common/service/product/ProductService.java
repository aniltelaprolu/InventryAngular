package com.inventory.common.service.product;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.inventory.common.constants.AppConstants.PRODUCTSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.product.Product;

public interface ProductService {
	public Product saveProductData(Product product);
	public Product getProductByName(String productName);
	public Product findById(Long productId);
	public List<Product> findAll(PageRequest pageRequest);
	public List<Product> getAllProduct();
	void updateProductStatus(PRODUCTSTATUS productStatus, Long productId);
	void deleteProductById(Long productId);
	public Long countByProductStatus(PRODUCTSTATUS productStatus);
    public Long countByStatus(STATUS status);
}
