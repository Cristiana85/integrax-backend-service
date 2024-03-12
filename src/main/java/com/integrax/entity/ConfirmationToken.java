package com.integrax.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Table(schema = "integrax", name = "confirmation_token", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"token"})
})
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id")
    private Long id;
	
	@Column(name = "user_id")
	private Long userId;

    @Column(name="token")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable=false, updatable=false)
	private User user;
}