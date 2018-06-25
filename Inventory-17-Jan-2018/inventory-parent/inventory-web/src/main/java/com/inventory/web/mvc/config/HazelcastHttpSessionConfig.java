package com.inventory.web.mvc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.hazelcast.HazelcastSessionRepository;
import org.springframework.session.hazelcast.PrincipalNameExtractor;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapAttributeConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.inventory.web.util.session.ObjectStreamSerializer;

@Configuration
@EnableHazelcastHttpSession(maxInactiveIntervalInSeconds = 120)
public class HazelcastHttpSessionConfig {

	 private static Logger logger = LoggerFactory.getLogger(HazelcastHttpSessionConfig.class);
	 public static HazelcastInstance HAZEL_CAST_INSTANCE = null;
	 
	 @Bean(destroyMethod = "shutdown")
	 public HazelcastInstance hazelcastInstance() {
		logger.info("HazelcastInstance configuration started");
        MapAttributeConfig attributeConfig = new MapAttributeConfig()
                        .setName(HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
                        .setExtractor(PrincipalNameExtractor.class.getName());

        Config config = new Config();
		SerializerConfig serializer = new SerializerConfig()
				.setImplementation(new ObjectStreamSerializer())
				.setTypeClass(Object.class);

		config.getSerializationConfig()
				.addSerializerConfig(serializer);

        String sessionMapName = "spring:session:sessions";
		config.getMapConfig(sessionMapName) 
                        .addMapAttributeConfig(attributeConfig)
                        .addMapIndexConfig(new MapIndexConfig(
                                        HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));

		HAZEL_CAST_INSTANCE = Hazelcast.newHazelcastInstance(config); 
		return HAZEL_CAST_INSTANCE;
	}
	 
	 @Bean
	 public HttpSessionEventPublisher httpSessionEventPublisher() {
		 return new HttpSessionEventPublisher();
	 }
	 
	 @Bean
	 public SessionRepository mapSessionRepository() {
		 return new MapSessionRepository();
	 }

}
