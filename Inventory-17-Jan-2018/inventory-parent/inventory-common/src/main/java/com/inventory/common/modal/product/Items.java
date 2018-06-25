package com.inventory.common.modal.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.inventory.common.constants.AppConstants.ITEMSTATUS;

@Entity
@Table(name="Items")
public class Items {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="item_id")
	private Long itemId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private ITEMSTATUS status;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public ITEMSTATUS getStatus() {
		return status;
	}

	public void setStatus(ITEMSTATUS status) {
		this.status = status;
	}
	
}
