package com.inventory.common.service.impl.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.constants.AppConstants.PRODUCTSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.constants.BaseDAOConstants;
import com.inventory.common.modal.product.Product;
import com.inventory.common.repository.product.ProductRepository;
import com.inventory.common.service.product.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product saveProductData(Product product) {
		Product product1=productRepository.save(product);
		return product1;
	}

	@Override
	public Product getProductByName(String productName) {
		return productRepository.findByproductName(productName);
	}

	@Override
	public List<Product> findAll(PageRequest pageRequest) {
		if(pageRequest == null) {
			pageRequest = new PageRequest(0, BaseDAOConstants.DEFAULT_PAGE_SIZE);
		}
		Page<Product> invProductPage = productRepository.findAll(pageRequest);
		return invProductPage.getContent();
	}

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(Long productId) {
		Product product=null;
		if(productId!=null)
		product = productRepository.findByProductId(productId);
		return product;
		
	}

	@Transactional
	@Override
	public void updateProductStatus(PRODUCTSTATUS productStatus, Long productId) {
		productRepository.updateProductStatus(productStatus, productId);
	}

	@Transactional
	@Override
	public void deleteProductById(Long productId) {
		if(productId!=null) {
			productRepository.deleteByProductId(productId);
		}
	}

	@Override
	public Long countByProductStatus(PRODUCTSTATUS productStatus) {
		return productRepository.countByProductStatus(productStatus);
	}

	@Override
	public Long countByStatus(STATUS status) {
		return productRepository.countByStatus(status);
	}

}
