package com.inventory.web.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.inventory.common.util.mail.DatabaseTemplateResolver;

@Configuration
public class MailConfig {

	@Bean
    public TemplateEngine emailTemplateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(dbTemplateResolver());
		return templateEngine;
	}
	
	@Bean
	public ITemplateResolver dbTemplateResolver() {
		final DatabaseTemplateResolver databaseTemplateResolver = new DatabaseTemplateResolver();
		databaseTemplateResolver.setOrder(Integer.valueOf(1));
		databaseTemplateResolver.setTemplateMode(TemplateMode.HTML);
		databaseTemplateResolver.setCacheable(Boolean.FALSE);
		return databaseTemplateResolver;
	}
	
	/*private ITemplateResolver stringTemplateResolver() {
        final StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(3));
        // No resolvable pattern, will simply process as a String template everything not previously matched
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

	private ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(2));
        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
        templateResolver.setPrefix("/mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
        templateResolver.setCacheable(false);
        return templateResolver;
    }*/

}
