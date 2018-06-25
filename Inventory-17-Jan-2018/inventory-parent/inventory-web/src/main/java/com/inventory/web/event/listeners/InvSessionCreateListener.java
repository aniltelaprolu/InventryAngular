package com.inventory.web.event.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.session.MapSession;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class InvSessionCreateListener implements ApplicationListener<SessionCreatedEvent> {

	private static Logger logger = LoggerFactory.getLogger(InvSessionCreateListener.class);
	
	@Override
	public void onApplicationEvent(SessionCreatedEvent event) {
		logger.info("Start Session created");
		MapSession session = event.getSession();
		logger.info("End Session created");
	}

}
