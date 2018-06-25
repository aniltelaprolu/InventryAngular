package com.inventory.common.service.vendor;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.inventory.common.modal.vendor.InvVendor;

public interface InvVendorService {
	public void saveVendorData(InvVendor vendor);
	public List<InvVendor> findAll(PageRequest pageRequest);
	public InvVendor findByName(String name);
	public InvVendor findByVendorId(Long id);
	public void DeleteVendorById(Long id);
	public List<InvVendor> getAllVendor();
	public InvVendor findByEmail(String email);
	public InvVendor findVendor();
}