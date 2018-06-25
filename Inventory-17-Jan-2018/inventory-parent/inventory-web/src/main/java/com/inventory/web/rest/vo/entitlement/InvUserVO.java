package com.inventory.web.rest.vo.entitlement;

import java.util.List;
import java.util.Set;

public class InvUserVO {

	private String userEmail;
	
	private String firstName;
	
	private String lastName;
	
	private Long userId;
	
	private String status;
	
	private String role;
	
	private List<InvUserAddressVO> invUserAddresses;
	
	private Set<InvUserPhoneVO> invUserPhones;

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the invUserAddresses
	 */
	public List<InvUserAddressVO> getInvUserAddresses() {
		return invUserAddresses;
	}

	/**
	 * @param invUserAddresses the invUserAddresses to set
	 */
	public void setInvUserAddresses(List<InvUserAddressVO> invUserAddresses) {
		this.invUserAddresses = invUserAddresses;
	}

	/**
	 * @return the invUserPhones
	 */
	public Set<InvUserPhoneVO> getInvUserPhones() {
		return invUserPhones;
	}

	/**
	 * @param invUserPhones the invUserPhones to set
	 */
	public void setInvUserPhones(Set<InvUserPhoneVO> invUserPhones) {
		this.invUserPhones = invUserPhones;
	}
}
