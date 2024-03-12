package com.integrax.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrax.entity.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
	
	Optional<ConfirmationToken> findByToken(String token);
	
}