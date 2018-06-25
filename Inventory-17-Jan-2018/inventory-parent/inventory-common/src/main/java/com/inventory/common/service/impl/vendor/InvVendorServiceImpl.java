package com.inventory.common.service.impl.vendor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.constants.BaseDAOConstants;
import com.inventory.common.modal.entitlement.InvUser;
import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.repository.vendor.InvVendorRepository;
import com.inventory.common.service.vendor.InvVendorService;

@Service("vendorService")
public class InvVendorServiceImpl implements InvVendorService {
	private static final Logger logger = LoggerFactory.getLogger(InvVendorServiceImpl.class);
	
	@Autowired
	private InvVendorRepository vendorRepository;

	@Override
	public void saveVendorData(InvVendor vendor) {
		if(vendor!=null) {
		vendorRepository.save(vendor);
		}
	}
	
	
	@Override
	public InvVendor findByName(String name) {
		return vendorRepository.findByName(name);
	}


	@Override
	public InvVendor findByVendorId(Long id) {
		return vendorRepository.findByVenderId(id);
	}


	@Override
	@Transactional
	public void DeleteVendorById(Long id) {
		vendorRepository.deleteByVenderId(id);
	}


	
	@Override
	public List<InvVendor> getAllVendor() {
		List<InvVendor> allVendor=null;
		allVendor=vendorRepository.findAll();
		return allVendor;
	}


	@Override
	public List<InvVendor> findAll(PageRequest pageRequest) {
		if(pageRequest == null) {
			pageRequest = new PageRequest(0, BaseDAOConstants.DEFAULT_PAGE_SIZE);
		}
		Page<InvVendor> invVendorPage = vendorRepository.findAll(pageRequest);
		return invVendorPage.getContent();
	}


	@Override
	public InvVendor findByEmail(String email) {
		return vendorRepository.findByEmail(email);
	}


	@Override
	public InvVendor findVendor() {
		InvVendor ven=vendorRepository.findTop1ByOrderByVenIdDesc();
		return ven;
	}

}
