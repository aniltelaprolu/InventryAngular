package com.inventory.common.util.mail;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;

import com.inventory.common.modal.EmailTemplate;
import com.inventory.common.repository.EmailTemplateRepository;

public class DatabaseTemplateResolver extends StringTemplateResolver {
	private final static String PREFIX = "";

    @Autowired 
    private EmailTemplateRepository emailTemplateRepository;

    public DatabaseTemplateResolver() {
    	Set<String> set = new HashSet<String>();
    	set.add(PREFIX + "*");
        setResolvablePatterns(set);
    }

    @Override
    protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration, String ownerTemplate, String template, Map<String, Object> templateResolutionAttributes) {
        EmailTemplate emailTemplate = emailTemplateRepository.findByName(template);  
        if (emailTemplate != null) {
            return super.computeTemplateResource(configuration, ownerTemplate, emailTemplate.getTemplate(), templateResolutionAttributes);
        }
        return null;
    }

}
