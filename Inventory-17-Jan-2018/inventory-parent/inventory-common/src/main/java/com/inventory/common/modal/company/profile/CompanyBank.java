/**
 * 
 */
package com.inventory.common.modal.company.profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.inventory.common.modal.base.BaseAuditModel;

/**
 * @author ES002
 *
 */
@Entity
@Table(name="Company_bank")
public class CompanyBank extends BaseAuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 354484313644844088L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="company_bank_id")
	private Long companyBankId;
	
	@Column(name="account_holder_name")
	private String accountHolderName;
	
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="branch")
	private String branch;
	
	@Column(name="account_number")
	private Long accountNumber;
	
	@Column(name="ifsc_code")
	private String ifscCode;

	/**
	 * @return the companyBankId
	 */
	public Long getCompanyBankId() {
		return companyBankId;
	}

	/**
	 * @param companyBankId the companyBankId to set
	 */
	public void setCompanyBankId(Long companyBankId) {
		this.companyBankId = companyBankId;
	}

	/**
	 * @return the accountHolderName
	 */
	public String getAccountHolderName() {
		return accountHolderName;
	}

	/**
	 * @param accountHolderName the accountHolderName to set
	 */
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * @return the accountNumber
	 */
	public Long getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the ifscCode
	 */
	public String getIfscCode() {
		return ifscCode;
	}

	/**
	 * @param ifscCode the ifscCode to set
	 */
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	
}
