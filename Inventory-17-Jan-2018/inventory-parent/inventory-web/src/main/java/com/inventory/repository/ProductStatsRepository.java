package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.modal.reports.ProductStats;

@Repository
public interface ProductStatsRepository extends JpaRepository<ProductStats, Long>{
	@Modifying
	@Query("update ProductStats p set p.status = :status")
	void updateStatus(@Param(value="status") STATUS status);
	
	ProductStats findByStatus(STATUS status);
}
