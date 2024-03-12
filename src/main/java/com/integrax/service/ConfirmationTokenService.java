package com.integrax.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.Lock;

import com.integrax.dto.ConfirmationTokenDTO;

import jakarta.persistence.LockModeType;

public interface ConfirmationTokenService {

	Optional<ConfirmationTokenDTO> getByToken(String confirmationToken);

	@Lock(LockModeType.OPTIMISTIC)
	void save(ConfirmationTokenDTO confirmationToken);

}
