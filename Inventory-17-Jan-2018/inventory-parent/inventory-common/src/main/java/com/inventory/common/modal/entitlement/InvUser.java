package com.inventory.common.modal.entitlement;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import com.inventory.common.constants.AppConstants.USERSTATUS;
import com.inventory.common.modal.base.BaseAuditModel;

@Entity
@Table(name = "inv_user")
public class InvUser extends BaseAuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2152659656083829881L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "inv_user_id")
	private Long id;
	
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Column(name = "password")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@Transient
	private String password;
	
	@Column(name = "firstname")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	@Column(name = "lastname")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private USERSTATUS status;
	
	@Column(name = "failed_attempts")
	private Integer failedAttempts;
	
	@Column(name="locked")
	private Integer locked;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_role_id", nullable = false)
	private Role role;
	
	@Column(name = "first_time")
	private Integer isFirstTime;
	
	@OneToMany(targetEntity=InvUserAddress.class,fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name = "inv_user_id",nullable=false)
	private List<InvUserAddress> invUserAddress;
	

	@OneToMany(targetEntity=InvUserPhone.class,fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name = "inv_user_id",nullable=false)
	private Set<InvUserPhone> invUserPhones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the status
	 */
	public USERSTATUS getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(USERSTATUS status) {
		this.status = status;
	}

	public Integer getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(Integer failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Boolean isLocked() {
		if(this.locked == null || this.locked != 1) {
			return Boolean.FALSE;
		}else {
			return Boolean.TRUE;
		}
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getIsFirstTime() {
		return isFirstTime;
	}

	public void setIsFirstTime(Integer isFirstTime) {
		this.isFirstTime = isFirstTime;
	}
	
	public Boolean isFirstTime() {
		if(this.isFirstTime == null || this.isFirstTime != 1) {
			return Boolean.FALSE;
		}else {
			return Boolean.TRUE;
		}
	}

	/**
	 * @return the invUserAddress
	 */
	public List<InvUserAddress> getInvUserAddress() {
		return invUserAddress;
	}

	/**
	 * @param invUserAddress the invUserAddress to set
	 */
	public void setInvUserAddress(List<InvUserAddress> invUserAddress) {
		this.invUserAddress = invUserAddress;
	}

	/**
	 * @return the invUserPhones
	 */
	public Set<InvUserPhone> getInvUserPhones() {
		return invUserPhones;
	}

	/**
	 * @param invUserPhones the invUserPhones to set
	 */
	public void setInvUserPhones(Set<InvUserPhone> invUserPhones) {
		this.invUserPhones = invUserPhones;
	}
}
