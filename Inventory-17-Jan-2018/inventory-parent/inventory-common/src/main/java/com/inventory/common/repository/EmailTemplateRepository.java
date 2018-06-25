package com.inventory.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.EmailTemplate;


@Repository("emailTemplateRepository")
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {
	EmailTemplate findByName(String name);
}
