package com.inventory.web.rest.controller.reports;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.modal.reports.ProductStats;
import com.inventory.service.ProductStatsService;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.reports.ProductStatsVO;

@RestController
@RequestMapping("service/reports")
public class ProductStatsReportRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductStatsReportRestController.class);
	
	@Autowired
	private ProductStatsService productStatsService;
	
	@RequestMapping(value = "/getBasicProductStats", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<ProductStatsVO>> getBasicProductStats() {
		logger.info("Product stats report started");
		ResponseVO<ProductStatsVO> message = new  ResponseVO<ProductStatsVO>();
		try {
			List<ProductStatsVO> productStatsVOs = new ArrayList<ProductStatsVO>();
			ProductStats productStats = productStatsService.getProductStatsByStatus(STATUS.ACTIVE);
			
			ProductStatsVO productStatsVO = new ProductStatsVO();
			if(productStats == null) {
				productStatsVOs.add(productStatsVO);
				message.setMessageId("PRODUCT_STATS_REPORT_SUCCESS");
				message.setMessageDetail("No Active records found for current report");
				message.setData(productStatsVOs);
				
				logger.info("No Active records found for product stats report");
				return new ResponseEntity<ResponseVO<ProductStatsVO>>(message, HttpStatus.OK);
			}
			
			productStatsVO.setTotalNoOfActiveProducts(productStats.getTotalNoOfActiveProducts());
			productStatsVO.setTotalNoOfInActiveProducts(productStats.getTotalNoOfInActiveProducts());
			productStatsVO.setTotalNoOfProducts(productStats.getTotalNoOfProducts());
			productStatsVO.setTotalNoOfAvailableProducts(productStats.getTotalNoOfAvailableProducts());
			productStatsVO.setTotalNoOfBroadcastedProducts(productStats.getTotalNoOfBroadcastedProducts());
			productStatsVO.setTotalNoOfUnAvailableProducts(productStats.getTotalNoOfUnAvailableProducts());
			
			productStatsVOs.add(productStatsVO);
			message.setMessageId("PRODUCT_STATS_REPORT_SUCCESS");
			message.setMessageDetail("Product stats report successfull");
			message.setData(productStatsVOs);
			
			logger.info("Product stats report ended");
			return new ResponseEntity<ResponseVO<ProductStatsVO>>(message, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occured while performing getting product stats report", e);
			message.setMessageId("PRODUCT_STATS_REPORT_FAILED");
			message.setMessageDetail("Product stats report failed");
			return new ResponseEntity<ResponseVO<ProductStatsVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
}
