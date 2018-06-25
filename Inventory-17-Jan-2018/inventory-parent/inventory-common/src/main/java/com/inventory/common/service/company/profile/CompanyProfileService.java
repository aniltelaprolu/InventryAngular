/**
 * 
 */
package com.inventory.common.service.company.profile;

import com.inventory.common.modal.company.profile.CompanyBank;
import com.inventory.common.modal.company.profile.CompanyProfile;

/**
 * @author ES002
 *
 */
public interface CompanyProfileService {
	public CompanyProfile findByCompanyId(Long companyId);
	
	public CompanyProfile saveCompanyDetails(CompanyProfile companyProfile);
	
	public CompanyProfile getdetails();
	
}
