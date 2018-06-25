/**
 * 
 */
package com.inventory.common.repository.company.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.company.profile.CompanyBank;
import com.inventory.common.modal.company.profile.CompanyProfile;

/**
 * @author ES002
 *
 */
@Repository("companyProfileRepository")
public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Long> {
	@Query(value="SELECT c FROM CompanyProfile c")
	public CompanyProfile getDetails();
	

}
