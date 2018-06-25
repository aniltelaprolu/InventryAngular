/**
 * 
 */
package com.inventory.common.repository.vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.vendor.VendorBankAccountDetails;

/**
 * @author ES002
 *
 */
@Repository("vendorBankDetailsRepository")
public interface VendorBankDetailsRepository extends JpaRepository<VendorBankAccountDetails, Long> {

}
