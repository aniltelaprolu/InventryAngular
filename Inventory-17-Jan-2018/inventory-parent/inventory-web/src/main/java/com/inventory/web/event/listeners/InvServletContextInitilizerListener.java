package com.inventory.web.event.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class InvServletContextInitilizerListener implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(InvServletContextInitilizerListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("inventory context initialized");
		/*ServletContext sc = sce.getServletContext(); 
        Map<String,ExpiringSession> sessions = HazelcastHttpSessionConfig.HAZEL_CAST_INSTANCE.getMap("spring:session:sessions");

    	SessionRepository<ExpiringSession> sessionRepository =
    			new MapSessionRepository(sessions);
    	SessionRepositoryFilter<ExpiringSession> filter =
    			new SessionRepositoryFilter<ExpiringSession>(sessionRepository);
    	Dynamic fr = sc.addFilter("springSessionFilter", filter);
    	fr.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/login");*/
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//HazelcastHttpSessionConfig.HAZEL_CAST_INSTANCE.shutdown();
	}

}
