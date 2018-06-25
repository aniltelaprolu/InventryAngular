/**
 * 
 */
package com.inventory.common.service.impl.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.common.modal.vendor.VendorBankAccountDetails;
import com.inventory.common.repository.vendor.VendorBankDetailsRepository;
import com.inventory.common.service.vendor.VendorBankDetailsService;

/**
 * @author ES002
 *
 */
@Service("vendorBankDetailsService")
public class VendorBankDetailsServiceImpl implements VendorBankDetailsService {
	
	@Autowired
	private VendorBankDetailsRepository vendorBankDetailsRepository;

	/* (non-Javadoc)
	 * @see com.inventory.common.service.vendor.VendorBankDetailsService#saveVendorBankData(com.inventory.common.modal.vendor.VendorBankAccountDetails)
	 */
	@Override
	public void saveVendorBankData(VendorBankAccountDetails details) {
		vendorBankDetailsRepository.save(details);
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.vendor.VendorBankDetailsService#findById(java.lang.Long)
	 */
	@Override
	public VendorBankAccountDetails findById(Long vendorAccountId) {
		return vendorBankDetailsRepository.findOne(vendorAccountId);
	}

}
