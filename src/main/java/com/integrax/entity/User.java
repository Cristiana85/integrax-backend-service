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

@Data
@Entity
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Table(schema = "integrax", name = "user", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"email"})
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id")
    private Long id;

//	@Column(name = "provider_user_id")
//	private String providerUserId;

	@Column(name = "email")
	private String email;

//	@Column(name = "enabled")
//	private boolean enabled;
//
//	@Column(name = "display_name")
//	private String displayName;
//
//	@Column(name = "created_date")
//	@Temporal(TemporalType.TIMESTAMP)
//	protected Date createdDate;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	protected Date modifiedDate;
//
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private Boolean enabled;
//
//	private String provider;
}
