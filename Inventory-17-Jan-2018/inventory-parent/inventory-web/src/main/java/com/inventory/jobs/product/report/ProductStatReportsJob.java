/*package com.inventory.jobs.product.report;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

import com.inventory.common.configuration.QuartzConfig;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.service.ProductStatsService;

@Component(value = "productReportJob")
public class ProductStatReportsJob implements Job {

	private static final Logger logger = LoggerFactory.getLogger(ProductStatReportsJob.class); 
	
	@Value("${cron.frequency.producStatReportTriggerExpression}")
	private String frequency;
	
	@Autowired
	private ProductStatsService productStatsService; 

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		logger.info("Running ProductStatReportsJob | frequency {}", frequency);
		productStatsService.updateStatus(STATUS.INACTIVE);
		productStatsService.addProductStat();
	}

	@Bean(name = "productStatReportsJob")
	public JobDetailFactoryBean sampleJob() {
		return QuartzConfig.createJobDetail(this.getClass());
	}

	@Bean(name = "productStatReportsTrigger")
	public CronTriggerFactoryBean sampleJobTrigger(@Qualifier("productStatReportsJob") JobDetail jobDetail) {
		return QuartzConfig.createCronTrigger(jobDetail, frequency);
	}
}
*/