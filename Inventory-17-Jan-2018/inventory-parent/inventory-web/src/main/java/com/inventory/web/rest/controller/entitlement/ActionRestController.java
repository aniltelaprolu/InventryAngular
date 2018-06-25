package com.inventory.web.rest.controller.entitlement;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.common.modal.entitlement.Action;
import com.inventory.common.service.entitlement.ActionService;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.base.SearchRequestVO;
import com.inventory.web.rest.vo.base.SearchResponseVO;
import com.inventory.web.rest.vo.entitlement.ActionVO;

@RestController
@RequestMapping("/service/action")
public class ActionRestController {

	private static final Logger logger = LoggerFactory.getLogger(ActionRestController.class);
	
	@Autowired
	private ActionService actionService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public ResponseEntity<ResponseVO<ActionVO>> getAll() {
		logger.info("start :: executing get all operation for actions");
		ResponseVO<ActionVO> message = new  ResponseVO<ActionVO>();
		try {
			
			List<Action> actions = actionService.findAll();
			
			List<ActionVO> actionVos = new ArrayList<ActionVO>();
			ActionVO actionVo = null;
			for(Action action : actions) {
				actionVo = new ActionVO();
				actionVo.setActionId(action.getActionId());
				actionVo.setAction(action.getAction());
				actionVo.setActionKey(action.getActionKey());
				actionVo.setUrl(action.getUrl());
				actionVo.setEnableFlag(action.getEnable());
				actionVos.add(actionVo);
			}
			
			message.setMessageId("GET_ALL_ACTIONS");
			message.setMessageDetail("get all actions");
			message.setData(actionVos);
			logger.info("end :: executing get all operation for actions");
			return new ResponseEntity<ResponseVO<ActionVO>>(message, HttpStatus.CREATED);
		}catch(Exception e) {
			logger.error("Error occured while performing get all actions operation", e);
			message.setMessageId("GET_ALL_ACTIONS_FAILED");
			message.setMessageDetail("Error occured while performing get all actions operation");
			return new ResponseEntity<ResponseVO<ActionVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value="/getAll/{enabled}", method=RequestMethod.GET)
	public ResponseEntity<SearchResponseVO<ActionVO>> getAllByEnableFlag(@PathVariable Boolean enabled, @RequestBody SearchRequestVO searchRequestVO) {
		logger.info("start :: executing get all operation using enabled flag for actions");
		SearchResponseVO<ActionVO> message = new  SearchResponseVO<ActionVO>();
		try {
			Integer pageNo = searchRequestVO.getPageNo();
			Integer pageSize = searchRequestVO.getPageSize();
			
			PageRequest pageRequest = new PageRequest(pageNo, pageSize);
			List<Action> actions = actionService.findByEnableFlag(enabled, pageRequest);
			
			List<ActionVO> actionVos = new ArrayList<ActionVO>();
			ActionVO actionVo = null;
			for(Action action : actions) {
				actionVo = new ActionVO();
				actionVo.setActionId(action.getActionId());
				actionVo.setAction(action.getAction());
				actionVo.setActionKey(action.getActionKey());
				actionVo.setUrl(action.getUrl());
				actionVo.setEnableFlag(action.getEnable());
				actionVos.add(actionVo);
			}
			
			message.setMessageId("GET_ACTIONS_USING_ENABLE_FLAG");
			message.setMessageDetail("get all action with enable flag : " + enabled);
			message.setcurrentPage(pageNo);
			message.setData(actionVos);
			logger.info("end :: executing get all operation using enable flag for actions");
			return new ResponseEntity<SearchResponseVO<ActionVO>>(message, HttpStatus.CREATED);
		}catch(Exception e) {
			logger.error("Error occured while performing get all operation using enable flag for actions", e);
			message.setMessageId("GET_ACTIONS_FAILED");
			message.setMessageDetail("Error occured while performing get all operation using enable flag for action");
			return new ResponseEntity<SearchResponseVO<ActionVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<ActionVO>> updateAction(@RequestBody List<ActionVO> actionVos) {
		logger.info("start :: update service for action executing");
		ResponseVO<ActionVO> message = new  ResponseVO<ActionVO>();

		if(actionVos == null ) {
			message.setMessageId("BLANK_ACTIONS");
			message.setMessageDetail("Actions can not be blank");
			return new ResponseEntity<ResponseVO<ActionVO>>(message, HttpStatus.METHOD_NOT_ALLOWED);
		}

		StringBuilder messageStr = new StringBuilder("enable flag updated for action keys: ");
		
		try {
			for(ActionVO actionVo : actionVos) {
				String actionKey = actionVo.getActionKey();
				actionService.updateEnableFlag(actionKey, actionVo.getEnableFlag());
				messageStr.append(actionKey + ",");
			}
			message.setMessageId("ACTION_UPDATED");
			message.setMessageDetail(messageStr.toString());
			logger.info("end :: update service for action executing");
			return new ResponseEntity<ResponseVO<ActionVO>>(message, HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error occured while performing update actions operation", e);
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Error occured while updating Action flag, total no of updated flag message: " +messageStr.toString());
			return new ResponseEntity<ResponseVO<ActionVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
}
