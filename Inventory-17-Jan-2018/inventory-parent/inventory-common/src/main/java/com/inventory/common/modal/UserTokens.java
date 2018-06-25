package com.inventory.common.modal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.inventory.common.modal.entitlement.InvUser;

@Entity
@Table(name = "token")
public class UserTokens implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "token_id")
	private Long id;
	
	@Column(name = "type", length = 50)
	private String type;
	
	@Column(name = "status", length = 10)
	private String status;
	
	@Column(name = "tokenstr", length = 250)
	private String tokenstr;
	
	@Column(name = "expiration_period")//in millis
	private Long expirationPeriod;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inv_user_id", nullable = false)
	private InvUser user;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTokenstr() {
		return tokenstr;
	}
	public void setTokenstr(String tokenstr) {
		this.tokenstr = tokenstr;
	}
	public Long getExpirationPeriod() {
		return expirationPeriod;
	}
	public void setExpirationPeriod(Long expirationPeriod) {
		this.expirationPeriod = expirationPeriod;
	}
	public InvUser getUser() {
		return user;
	}
	public void setUser(InvUser user) {
		this.user = user;
	}
}
