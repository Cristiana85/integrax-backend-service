package com.integrax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrax.entity.Template;

import lombok.NonNull;

public interface TemplateRepository extends JpaRepository<Template, Long> {
	
	List<Template> findByTenantId(@NonNull Long tenantId);
	
}