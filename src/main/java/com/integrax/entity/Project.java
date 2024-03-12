package com.integrax.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Table(schema = "integrax", name = "project", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"tenant_id"})
})
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id")
	private Long id;

	@NotNull(message = "{NotNullTenantIdValidation}")
	@Column(name = "tenant_id")
	private Long accountId;


//	@Column(name = "info", columnDefinition = "jsonb")
//	@Convert(converter = MapConverter.class)
//	@ColumnTransformer(write = "?::jsonb")
//	//@Type(type = "jsonb")
//	private Map<String, Object> info;
	
	@NotNull(message = "{NotNullNameValidation}")
	@Column(name = "name")
	private String name;
	
}
