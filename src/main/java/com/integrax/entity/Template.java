package com.integrax.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.NotNull;


@Data
@Entity
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Table(schema = "integrax", name = "template", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"tenant_id"})
})
public class Template implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id")
    private Long id;
	
	@NotNull(message = "{NotNullTenantIdValidation}")
	@Column(name = "tenant_id")
	private Long tenantId;

}
