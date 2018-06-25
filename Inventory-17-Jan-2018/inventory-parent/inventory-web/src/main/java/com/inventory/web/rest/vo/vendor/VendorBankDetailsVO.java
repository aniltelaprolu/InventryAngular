package com.inventory.web.rest.vo.vendor;


public class VendorBankDetailsVO {
	
	private Long vendorId;
	
	private Long vendorAccountId;
	
	private String accountHolderName;
	
	private String bankName;
	
	private String branch;
	
	private Long accountNumber;
	
	private String ifscCode;
	
	

	/**
	 * @return the vendorId
	 */
	public Long getVendorId() {
		return vendorId;
	}

	/**
	 * @param vendorId the vendorId to set
	 */
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

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
