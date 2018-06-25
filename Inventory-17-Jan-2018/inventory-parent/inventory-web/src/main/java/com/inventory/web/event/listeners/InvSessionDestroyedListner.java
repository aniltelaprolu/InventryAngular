package com.inventory.web.event.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.session.MapSession;
import org.springframework.session.SessionRepository;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

@Component
public class InvSessionDestroyedListner implements ApplicationListener<SessionDestroyedEvent> {

	private static Logger logger = LoggerFactory.getLogger(InvSessionDestroyedListner.class);

	@SuppressWarnings("rawtypes")
	@Autowired
	private SessionRepository sessionRepository;
	
	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		logger.info("Start Session destroyed");
		MapSession session = event.getSession();
		sessionRepository.delete(session.getId());
		logger.info("End Session destroyed");
	}

}
