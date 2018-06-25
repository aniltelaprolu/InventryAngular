/**
 * 
 */
package com.inventory.common.service.vendor;

import com.inventory.common.modal.vendor.VendorBankAccountDetails;

/**
 * @author ES002
 *
 */
public interface VendorBankDetailsService {
	public void saveVendorBankData(VendorBankAccountDetails details);
	public VendorBankAccountDetails findById(Long vendorAccountId);
}
