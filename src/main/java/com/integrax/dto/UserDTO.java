package com.integrax.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	private Long id;
	private String email;
	private String password;
	private Boolean enabled;
	
}

