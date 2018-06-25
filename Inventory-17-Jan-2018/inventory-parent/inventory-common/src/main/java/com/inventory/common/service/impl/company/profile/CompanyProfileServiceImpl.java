/**
 * 
 */
package com.inventory.common.service.impl.company.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.common.modal.company.profile.CompanyBank;
import com.inventory.common.modal.company.profile.CompanyProfile;
import com.inventory.common.repository.company.profile.CompanyProfileRepository;
import com.inventory.common.service.company.profile.CompanyProfileService;

/**
 * @author ES002
 *
 */
@Service("companyProfileService")
public class CompanyProfileServiceImpl implements CompanyProfileService {
	
	@Autowired
	private CompanyProfileRepository companyProfileRepository;

	/* (non-Javadoc)
	 * @see com.inventory.common.service.company.profile.CompanyProfileService#findByCompanyId(java.lang.Long)
	 */
	@Override
	public CompanyProfile findByCompanyId(Long companyId) {
		return companyProfileRepository.findOne(companyId);
	}

	@Override
	public CompanyProfile saveCompanyDetails(CompanyProfile companyProfile) {
		return companyProfileRepository.save(companyProfile);
	}

	@Override
	public CompanyProfile getdetails() {
		return companyProfileRepository.getDetails();
	}

}
