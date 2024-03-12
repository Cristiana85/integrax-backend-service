package com.integrax.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.integrax.dto.ResultDTO;
import com.integrax.entity.request.SignUpRequest;
import com.integrax.entity.request.SigninRequest;
import com.integrax.entity.response.JwtAuthenticationResponse;
import com.integrax.service.AuthenticationService;
import com.integrax.service.UserService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@NonNull
	private final UserService userService;

	@PostMapping(value="/signup")
	public ResponseEntity<ResultDTO<JwtAuthenticationResponse>> signup(
			@RequestBody SignUpRequest request) {
		return ResponseEntity.ok(authenticationService.signup(request));
	}

	@PostMapping(value="/signin")
	public ResponseEntity<ResultDTO<JwtAuthenticationResponse>> signin(
			@RequestBody SigninRequest request) {
		return ResponseEntity.ok(authenticationService.signin(request));
	}

	@PostMapping(value="/signout")
	public ResponseEntity<ResultDTO<JwtAuthenticationResponse>> logout(
			@RequestBody SigninRequest request) {
		return ResponseEntity.ok(authenticationService.signin(request));
	}

	@PostMapping(value="/forgot-password")
	public ResponseEntity<ResultDTO<JwtAuthenticationResponse>> forgotPassword(
			@RequestBody SigninRequest signinRequest) {
		return ResponseEntity.ok(authenticationService.forgotPassword(signinRequest));
	}
	    
//    @GetMapping("/verify")
//    public String verifyCustomer(@RequestParam(required = false) String token){
////        if(StringUtils.isEmpty(token)){
////            redirAttr.addFlashAttribute("tokenError", messageSource.getMessage(“user.registration.verification.missing.token”, null,LocaleContextHolder.getLocale()));
////            return "redirect:/login";
////        }
////        try {
////              userService.verifyUser(token);
////        } catch (InvalidTokenException e) {
////            redirAttr.addFlashAttribute(“tokenError”, messageSource.getMessage(“user.registration.verification.invalid.token”, null,LocaleContextHolder.getLocale()));
////            return "redirect:/login";
////        }
////
////        redirAttr.addFlashAttribute(“verifiedAccountMsg”, messageSource.getMessage(“user.registration.verification.success”, null,LocaleContextHolder.getLocale()));
//    	return "forward:/index.html";
//    }
    
    
	@PostMapping(value="/change-password")
	public ResponseEntity<ResultDTO<JwtAuthenticationResponse>> resetPassword(
			@RequestBody SigninRequest signinRequest) {
		return ResponseEntity.ok(authenticationService.resetPassword(signinRequest));
	}
	//    
	//    @PostMapping(value="/show-reset-password")
	//    public ResponseEntity<ResultDTO<ConfirmationTokenDTO>> showResetPasswordForm(
	//    		@RequestParam("token") String confirmationToken) {
	//        return ResponseEntity.ok(authenticationService.showResetPasswordForm(confirmationToken));
	//    }

	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public String confirmUserAccount(
			@RequestParam("token") String confirmationToken) {
		return userService.confirmEmail(confirmationToken);
	}
}