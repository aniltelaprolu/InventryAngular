package com.inventory.common.service.entitlement;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.inventory.common.modal.entitlement.Action;

public interface ActionService {
	Action findByActionKey(String actionKey);
	void updateEnableFlag(String actionKey, Boolean enable);
	List<Action> findByEnableFlag(Boolean enableFlag, PageRequest pageRequest);
	List<Action> findAll();
}
