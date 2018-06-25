package com.inventory.common.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_template")
public class EmailTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="email_template_id")
	private int emailTemplateId;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "status", length = 20)
	private String status;
	
	@Column(name = "template", columnDefinition = "TEXT", length = 65535)
	private String template;
	
	@Column(name = "template_param", length = 500)
	private String templateParam;
	
	public int getEmailTemplateId() {
		return emailTemplateId;
	}
	public void setEmailTemplateId(int emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getTemplateParam() {
		return templateParam;
	}
	public void setTemplateParam(String templateParam) {
		this.templateParam = templateParam;
	}
}
