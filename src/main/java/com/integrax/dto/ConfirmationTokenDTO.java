package com.integrax.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class ConfirmationTokenDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	private Long id;
	private Long userId;
	private String token;
	private Date createdDate;
	private Date expiredDate;
	
	private UserDTO user;
}

