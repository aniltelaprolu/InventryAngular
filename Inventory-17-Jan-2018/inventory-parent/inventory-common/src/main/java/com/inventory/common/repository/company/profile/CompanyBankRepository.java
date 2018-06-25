/**
 * 
 */
package com.inventory.common.repository.company.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.company.profile.CompanyBank;

/**
 * @author ES002
 *
 */
@Repository("companyBankRepository")
public interface CompanyBankRepository extends JpaRepository<CompanyBank,Long> {
	
}
