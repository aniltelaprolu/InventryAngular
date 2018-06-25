package com.inventory.common.repository.vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.vendor.InvVendor;

@Repository("vendorRepository")
public interface InvVendorRepository extends JpaRepository<InvVendor, Long>,JpaSpecificationExecutor<InvVendor>{
	InvVendor findByName(String name);
	InvVendor findByVenderId(Long id);
	void deleteByVenderId(Long id);
	InvVendor findByEmail(String email);
	//String findFirstByOrderByVenIdDesc();
	InvVendor findTop1ByOrderByVenIdDesc();
}