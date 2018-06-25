/**
 * 
 */
package com.inventory.common.modal.company.profile;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.inventory.common.modal.base.BaseAuditModel;

/**
 * @author ES002
 *
 */

@Entity
@Table(name = "Company_profile")
public class CompanyProfile extends BaseAuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8828288179210207726L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "company_name")
	private String companyName;
	
	@Column(name="gst_number")
	private String gstNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="website")
	private String website;

	@Column(name="description")
	private String description;
	
	@Column(name="phone")
	private Long phone;
	
	@OneToMany(targetEntity = CompanyAddress.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "company_id", nullable = false)
	private List<CompanyAddress> companyAddress;

	@OneToMany(targetEntity = CompanyBank.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "company_id", nullable = false)
	private List<CompanyBank> companyBank;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the gstNumber
	 */
	public String getGstNumber() {
		return gstNumber;
	}

	/**
	 * @param gstNumber the gstNumber to set
	 */
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	/**
	 * @return the companyAddress
	 */
	public List<CompanyAddress> getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * @param companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(List<CompanyAddress> companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * @return the companyBank
	 */
	public List<CompanyBank> getCompanyBank() {
		return companyBank;
	}

	/**
	 * @param companyBank the companyBank to set
	 */
	public void setCompanyBank(List<CompanyBank> companyBank) {
		this.companyBank = companyBank;
	}

	
}
