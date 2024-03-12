package com.integrax.service.impl;


import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrax.dto.ConfirmationTokenDTO;
import com.integrax.entity.ConfirmationToken;
import com.integrax.repository.ConfirmationTokenRepository;
import com.integrax.service.ConfirmationTokenService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

	@NonNull
	private final ConfirmationTokenRepository confirmationTokenRepo;
	
	@Autowired
	private final ModelMapper modelMapper;
	
	@Override
	@SneakyThrows
	public Optional<ConfirmationTokenDTO> getByToken(String confirmationToken) {
		return confirmationTokenRepo.findByToken(confirmationToken)
				.map(k -> modelMapper.map(k, ConfirmationTokenDTO.class));
	}

	@SneakyThrows
	public void save(ConfirmationTokenDTO confirmationToken) {
		confirmationTokenRepo.save(modelMapper.map(confirmationToken, ConfirmationToken.class));
		
	}
	
}
