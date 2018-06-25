package com.inventory.web.rest.vo.product;

import com.inventory.common.constants.AppConstants.ITEMSTATUS;

public class ItemsVO {
	
	private Long itemId;
	
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
