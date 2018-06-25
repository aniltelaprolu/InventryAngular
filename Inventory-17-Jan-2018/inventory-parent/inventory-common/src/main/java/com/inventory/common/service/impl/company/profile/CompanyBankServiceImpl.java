/**
 * 
 */
package com.inventory.common.service.impl.company.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.common.modal.company.profile.CompanyBank;
import com.inventory.common.repository.company.profile.CompanyBankRepository;
import com.inventory.common.service.company.profile.CompanyBankService;

/**
 * @author ES002
 *
 */
@Service("companyBankService")
public class CompanyBankServiceImpl implements CompanyBankService{
	
	@Autowired
	private CompanyBankRepository companyBankRepository;

	/* (non-Javadoc)
	 * @see com.inventory.common.service.company.profile.CompanyBankService#findByCompanyBankId(java.lang.Long)
	 */
	@Override
	public CompanyBank findByCompanyBankId(Long companyBankId) {
		return companyBankRepository.getOne(companyBankId);
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.company.profile.CompanyBankService#getAllBankDetails()
	 */
	@Override
	public List<CompanyBank> getAllBankDetails() {
		return companyBankRepository.findAll();
	}

}
