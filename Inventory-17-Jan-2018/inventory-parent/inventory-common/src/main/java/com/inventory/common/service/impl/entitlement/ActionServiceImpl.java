package com.inventory.common.service.impl.entitlement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.inventory.common.constants.BaseDAOConstants;
import com.inventory.common.modal.entitlement.Action;
import com.inventory.common.repository.entitlement.ActionRepository;
import com.inventory.common.service.entitlement.ActionService;

@Service("actionService")
public class ActionServiceImpl implements ActionService{

	private static final Logger logger = LoggerFactory.getLogger(ActionServiceImpl.class);
	
	@Autowired
	private ActionRepository actionRepository;
	
	@Override
	public Action findByActionKey(String actionKey) {
		logger.info("find action by key {}", actionKey);
		return actionRepository.findByActionKeyAndEnable(actionKey, Boolean.TRUE);
	}

	@Override
	public void updateEnableFlag(String actionKey, Boolean enabled) {
		logger.info("update enable flag with value {} for action key {}", enabled, actionKey);
		actionRepository.updateEnableFlag(enabled, actionKey);
	}

	@Override
	public List<Action> findByEnableFlag(Boolean enableFlag, PageRequest pageRequest) {
		logger.info("find all actions by flag {}", enableFlag);
		
		if(pageRequest == null) {
			pageRequest = new PageRequest(0, BaseDAOConstants.DEFAULT_PAGE_SIZE);
		}
		Page<Action> actions = actionRepository.findByEnable(enableFlag, pageRequest);
		return actions.getContent();
	}

	@Override
	public List<Action> findAll() {
		logger.info("find all actions");
		return actionRepository.findAll();
	}
	
}
