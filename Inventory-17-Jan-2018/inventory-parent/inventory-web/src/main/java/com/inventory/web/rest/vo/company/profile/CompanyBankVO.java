package com.inventory.web.rest.vo.company.profile;

public class CompanyBankVO {
	private Long companyBankId;

	private String accountHolderName;

	private String bankName;

	private String branch;

	private Long accountNumber;

	private String ifscCode;

	public Long getCompanyBankId() {
		return companyBankId;
	}

	public void setCompanyBankId(Long companyBankId) {
		this.companyBankId = companyBankId;
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
