package com.integrax.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class ProjectDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Long id;
	private Long accountId;
//	private Map<String, Object> info;
//	
//	private String json;
	
	private String name;
}

