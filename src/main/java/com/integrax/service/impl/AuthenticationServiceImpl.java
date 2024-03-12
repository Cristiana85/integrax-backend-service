package com.integrax.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.integrax.dto.ConfirmationTokenDTO;
import com.integrax.dto.ResultDTO;
import com.integrax.dto.UserDTO;
import com.integrax.entity.request.SignUpRequest;
import com.integrax.entity.request.SigninRequest;
import com.integrax.entity.response.JwtAuthenticationResponse;
import com.integrax.security.jwt.JwtUtils;
import com.integrax.security.services.UserDetailsImpl;
import com.integrax.service.AuthenticationService;
import com.integrax.service.ConfirmationTokenService;
import com.integrax.service.MailService;
import com.integrax.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	@NotNull
	private final UserService userService;

	@NotNull
	private final ConfirmationTokenService confirmationTokenService;

	@Autowired
	private final AuthenticationManager authenticationManager;

	@Autowired
	private final JwtUtils jwtUtils;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MailService mailService;
	
	@Override
	public ResultDTO<JwtAuthenticationResponse> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
		ResultDTO<JwtAuthenticationResponse> ret = new ResultDTO<>();
		ret.setSuccessful(true);

		try {
			ResultDTO<UserDTO> result = userService.registerNewUser(signUpRequest);
			if (result.isSuccessful()) {
				UserDTO user = result.getContent();

				ConfirmationTokenDTO confirmationToken = new ConfirmationTokenDTO();
				confirmationToken.setUserId(user.getId());
				confirmationToken.setUser(user);
				confirmationToken.setCreatedDate(new Date());
				confirmationToken.setToken(UUID.randomUUID().toString());

				confirmationTokenService.save(confirmationToken);

				//				token = jwtTokenUtil.generateToken(user.getEmail(), claims);
				//				user.setVerificationToken(token);
				//				user.setEnabled(false);
				//				
				//				userService.saveUser(user);

				log.info("Confirmation Token: " + confirmationToken);
				ret.setContent(JwtAuthenticationResponse.builder().token(confirmationToken.getToken()).build());
				
	            final String url= UriComponentsBuilder.fromHttpUrl("http://localhost:5052")
	                    .path("/authenticate/confirm-account").queryParam("token", confirmationToken.getToken()).toUriString();
	            
	            
	            mailService.sendEmail("casanovacristiana@gmail.com", "Complete Registration!", 
	        			"To confirm your account, please click here :" + url);
	            ret.append("We have sent a verification link to your email. Please check.");
				
//				log.info("http://localhost:5052/authenticate/confirm-account?token="+confirmationToken.getToken());

			} else {
				ret.append(result);
			}
		} catch (Exception e) {
			log.error("Exception Ocurred", e);
			//			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}
		return ret;
	}

	@Override
	public ResultDTO<JwtAuthenticationResponse> signin(SigninRequest signinRequest) {
		ResultDTO<JwtAuthenticationResponse> ret = new ResultDTO<JwtAuthenticationResponse>();
		ret.setSuccessful(Boolean.TRUE);
		
		try {
			UserDTO user = userService.getUserByEmail(signinRequest.getEmail());
			if (user != null) {
				if (user.getEnabled()){
					Authentication authentication = authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
					
					SecurityContextHolder.getContext().setAuthentication(authentication);
					String jwt = jwtUtils.generateJwtToken(authentication);
					
					UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();   
					
					//Check if user is enabled
					
					//		List<String> roles = userDetails.getAuthorities().stream()
					//				.map(item -> item.getAuthority())
					//				.collect(Collectors.toList());
					
					ret.setContent(new JwtAuthenticationResponse(jwt, 
							userDetails.getId(), 
							userDetails.getEmail()));
					
				} else {
					ret.setSuccessful(Boolean.FALSE);
					ret.append("User must be verified by email");
				}
			} else {
				ret.setSuccessful(Boolean.FALSE);
				ret.append("User not registered");
			}
		} catch (Exception e) {
			ret.setSuccessful(Boolean.FALSE);
			ret.append("Bad credentials");
		}
		
		return ret;
	}

	@Override
	public ResultDTO<JwtAuthenticationResponse> forgotPassword(SigninRequest signinRequest) {
		ResultDTO<JwtAuthenticationResponse> ret = new ResultDTO<JwtAuthenticationResponse>();
		ret.setSuccessful(Boolean.TRUE);
		
		try {
			UserDTO user = userService.getUserByEmail(signinRequest.getEmail());
	        if(user != null){
				
	        	ConfirmationTokenDTO confirmationToken = new ConfirmationTokenDTO();
	        	confirmationToken.setUserId(user.getId());
	        	confirmationToken.setCreatedDate(new Date());
//	        	confirmationToken.setExpiredDate(new Date()); + 7 gg
	        	confirmationToken.setUser(user);
	        	confirmationToken.setToken(UUID.randomUUID().toString());
	        	
	        	confirmationTokenService.save(confirmationToken);
	        	
	            final String url= UriComponentsBuilder.fromHttpUrl("http://localhost:4200")
	                    .path("/ch-password").queryParam("token", confirmationToken.getToken()).toUriString();
	            mailService.sendEmail("casanovacristiana@gmail.com", "Change password!", 
	        			"To confirm your account, please click here :" + url);
	            ret.append("We have sent a reset password link to your email. Please check.");
	            
	            log.info(url);
	            
	        } else {
	        	ret.setSuccessful(Boolean.FALSE);
	        	ret.append("Invalid user email");
	        }
		} catch (Exception e) {
			log.error(e.getMessage());
			ret.setSuccessful(Boolean.FALSE);
		}
		
		return ret;
	}
	
	@Override
	public ResultDTO<ConfirmationTokenDTO> showResetPasswordForm(String token) {
		ResultDTO<ConfirmationTokenDTO> ret = new ResultDTO<ConfirmationTokenDTO>();
		ret.setSuccessful(Boolean.TRUE);
		
		try {
			Optional<ConfirmationTokenDTO> optionalToken = confirmationTokenService.getByToken(token);
			if (!optionalToken.isEmpty()) {
				//FIXME
				ConfirmationTokenDTO confirmationToken = optionalToken.get();
				if (confirmationToken.getExpiredDate().after(new Date())) {
					ret.setContent(confirmationToken);
				} else {
					ret.setSuccessful(Boolean.FALSE);
					ret.append("Reset Token is expired");
				}
			}else {
				ret.setSuccessful(Boolean.FALSE);
				ret.append("Could not find reset token");
			}
		} catch (Exception e) {
			ret.setSuccessful(Boolean.FALSE);
		}
		
		return ret;
	}

	@Override
	public ResultDTO<JwtAuthenticationResponse> resetPassword(SigninRequest signinRequest) {
		ResultDTO<JwtAuthenticationResponse> ret = new ResultDTO<JwtAuthenticationResponse>();
		ret.setSuccessful(Boolean.TRUE);

		try {
			Optional<ConfirmationTokenDTO> optionalConfirmationToken = confirmationTokenService.getByToken(signinRequest.getToken());
			if (!optionalConfirmationToken.isEmpty()) {
				ConfirmationTokenDTO confirmationToken = optionalConfirmationToken.get();
				if (confirmationToken != null) {
					UserDTO user = confirmationToken.getUser();
					user.setPassword(passwordEncoder.encode(signinRequest.getPassword()));
					userService.saveUser(user);
					
					//delete confirmation record

					ret.append( "You have successfully changed your password.");
					ret.setContent(JwtAuthenticationResponse.builder().token(confirmationToken.getToken()).build());
				}
			}
		} catch (Exception e) {
			ret.setSuccessful(Boolean.FALSE);
		}

		return ret;
	}
}