package com.inventory.service;

import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.modal.reports.ProductStats;

public interface ProductStatsService {
	public void addProductStat();
	public void updateStatus(STATUS status);
	public ProductStats getProductStatsByStatus(STATUS status);
}
