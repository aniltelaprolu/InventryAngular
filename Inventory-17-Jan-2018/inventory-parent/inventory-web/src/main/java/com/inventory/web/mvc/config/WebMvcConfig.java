package com.inventory.web.mvc.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.inventory.web.security.CustomInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private CustomInterceptor customInterceptor;
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/changePassword").setViewName("changePassword");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/admin/modal-addform").setViewName("addProduct");
    }
	
	@Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("4096KB");
        factory.setMaxRequestSize("4096KB");
        return factory.createMultipartConfig();
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(customInterceptor)
        .addPathPatterns("/externalservice/**");
	 }
}
