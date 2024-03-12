package com.integrax.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.integrax.dto.HandleDTO;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Component
@RequiredArgsConstructor
public class IntegraUtils {
	
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@SneakyThrows
	public boolean comparePassword(@NonNull String encodedPassword, @NonNull  String rawPassword) throws Exception {
		return Argon2Calculator.match(rawPassword, encodedPassword) || 
				encoder().matches(
						rawPassword.replace(Constants.Constant.BCRYPT.getValue(), ""), 
						encodedPassword.replace(Constants.Constant.BCRYPT.getValue(), ""));
	}

	public static HandleDTO getHandleFromHeader(String authorization, String accountId) {
		HandleDTO handle = new HandleDTO(Long.valueOf(accountId));
		handle.setToken(authorization);
		return handle;
	}

}
