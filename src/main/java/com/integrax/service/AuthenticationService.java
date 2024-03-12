package com.integrax.service;

import com.integrax.dto.ConfirmationTokenDTO;
import com.integrax.dto.ResultDTO;
import com.integrax.entity.request.SignUpRequest;
import com.integrax.entity.request.SigninRequest;
import com.integrax.entity.response.JwtAuthenticationResponse;

public interface AuthenticationService {
	
    ResultDTO<JwtAuthenticationResponse> signup(SignUpRequest request);

    ResultDTO<JwtAuthenticationResponse> signin(SigninRequest request);

	ResultDTO<JwtAuthenticationResponse> forgotPassword(SigninRequest signinRequest);

	ResultDTO<JwtAuthenticationResponse> resetPassword(SigninRequest signinRequest);

	ResultDTO<ConfirmationTokenDTO> showResetPasswordForm(String token);

}