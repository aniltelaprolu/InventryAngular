package com.inventory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.constants.AppConstants.PRODUCTSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.service.product.ProductService;
import com.inventory.modal.reports.ProductStats;
import com.inventory.repository.ProductStatsRepository;
import com.inventory.service.ProductStatsService;

@Service(value = "ProductStatsService")
public class ProductStatsServiceImpl implements ProductStatsService{

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductStatsRepository productStatsRepository;
	
	@Override
	@Transactional
	public void addProductStat() {
		Long totalNoOfAvailableProducts = productService.countByProductStatus(PRODUCTSTATUS.AVAILABLE);
		Long totalNoOfUnAvailableProducts = productService.countByProductStatus(PRODUCTSTATUS.NOTAVAILABLE);
		Long totalNoOfActiveProducts = productService.countByStatus(STATUS.ACTIVE);
		Long totalNoOfInActiveProducts = productService.countByStatus(STATUS.INACTIVE);
		Long totalNoOfBroadcastedProducts = productService.countByProductStatus(PRODUCTSTATUS.BROADCASTED);
		Long totalNoOfProducts = totalNoOfActiveProducts + totalNoOfInActiveProducts;
		
		ProductStats productStats = new ProductStats();
		productStats.setTotalNoOfActiveProducts(totalNoOfActiveProducts);
		productStats.setTotalNoOfAvailableProducts(totalNoOfAvailableProducts);
		productStats.setTotalNoOfBroadcastedProducts(totalNoOfBroadcastedProducts);
		productStats.setTotalNoOfInActiveProducts(totalNoOfInActiveProducts);
		productStats.setTotalNoOfUnAvailableProducts(totalNoOfUnAvailableProducts);
		productStats.setTotalNoOfProducts(totalNoOfProducts);
		productStats.setStatus(STATUS.ACTIVE);
		
		productStatsRepository.save(productStats);
	}

	@Override
	@Transactional
	public void updateStatus(STATUS status) {
		productStatsRepository.updateStatus(status);
	}
	
	@Override
	@Transactional
	public ProductStats getProductStatsByStatus(STATUS status) {
		return productStatsRepository.findByStatus(status);
	}

}
