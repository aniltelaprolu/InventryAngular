package com.inventory.web.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@ComponentScan(basePackages = { "com.inventory", "com.inventory.common.utils", "com.inventory.common.modal" })
@EnableJpaRepositories(basePackages = { "com.inventory.common.repository", "com.inventory.repository" })
@EntityScan(basePackages = { "com.inventory.common.modal", "com.inventory.modal" })
@EnableAutoConfiguration
@PropertySource({ "classpath:application.properties" })
public class InventoryApplication {

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> {
			ErrorPage custom404page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
			container.addErrorPages(custom404page);
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
}
