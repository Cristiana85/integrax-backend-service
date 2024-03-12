package com.integrax.service;

import org.springframework.data.jpa.repository.Lock;

import com.integrax.dto.ResultDTO;
import com.integrax.dto.UserDTO;
import com.integrax.entity.request.SignUpRequest;

import jakarta.persistence.LockModeType;
import jakarta.validation.constraints.NotEmpty;

public interface UserService {
	
    public ResultDTO<UserDTO> registerNewUser(SignUpRequest signUpRequest) throws Exception;

	@Lock(LockModeType.OPTIMISTIC)
	public ResultDTO<UserDTO> saveUser(UserDTO user);

	public String confirmEmail(String confirmationToken);

	public ResultDTO<Void> recoverPassword(@NotEmpty String expiredToken);

	public UserDTO getUserByEmail(String email);
}