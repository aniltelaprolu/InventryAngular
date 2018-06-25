package com.inventory.common.modal.vendor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.inventory.common.modal.base.BaseAuditModel;

@Entity
@Table(name="vendor_bank_account_details")
public class VendorBankAccountDetails extends BaseAuditModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3234953334088970804L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="vendor_account_id")
	private Long vendorAccountId;
	
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
	
	

	public Long getVendorAccountId() {
		return vendorAccountId;
	}

	public void setVendorAccountId(Long vendorAccountId) {
		this.vendorAccountId = vendorAccountId;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

}
