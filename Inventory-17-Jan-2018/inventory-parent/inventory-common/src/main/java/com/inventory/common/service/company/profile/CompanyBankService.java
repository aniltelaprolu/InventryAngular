/**
 * 
 */
package com.inventory.common.service.company.profile;

import java.util.List;

import com.inventory.common.modal.company.profile.CompanyBank;

/**
 * @author ES002
 *
 */
public interface CompanyBankService {
	public CompanyBank findByCompanyBankId(Long companyBankId);
	
	public List<CompanyBank> getAllBankDetails();
	
}
