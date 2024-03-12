package com.integrax.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.integrax.dto.ConfirmationTokenDTO;
import com.integrax.dto.ResultDTO;
import com.integrax.dto.UserDTO;
import com.integrax.entity.User;
import com.integrax.entity.request.SignUpRequest;
import com.integrax.repository.UserRepository;
import com.integrax.service.ConfirmationTokenService;
import com.integrax.service.UserService;

import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private final UserRepository userRepository;

	@NonNull
	private final ConfirmationTokenService confirmationTokenService;

	@Autowired
	private final ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResultDTO<UserDTO> registerNewUser(SignUpRequest signUpRequest) throws Exception {
		ResultDTO<UserDTO> ret = new ResultDTO<>();
		ret.setSuccessful(true);

		try {
			if (!userRepository.existsByEmail(signUpRequest.getEmail())) {
				UserDTO user = new UserDTO();
				user.setEmail(signUpRequest.getEmail());
				user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
				user.setEnabled(Boolean.FALSE);

				User userDB = userRepository.save(modelMapper.map(user, User.class));
				ret.setContent(getUser(userDB.getId()));
			} else {
				ret.setSuccessful(false);
				ret.append("User with email id " + signUpRequest.getEmail() + " already exists");
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return ret;
	}

	@SneakyThrows
	private UserDTO getUser(Long id) {
		Optional<UserDTO> optionalUser = userRepository.findById(id)
				.stream()
				.map(user -> modelMapper.map(user, UserDTO.class))
				.findFirst();
		return Optional.ofNullable(optionalUser.get()).orElseThrow();
	}

	@Override
	public String confirmEmail(String token) {
		String ret = "";
		try {
			Optional<ConfirmationTokenDTO> optionalConfirmationToken = confirmationTokenService.getByToken(token);

			if(optionalConfirmationToken.get() != null) {
				ConfirmationTokenDTO confirmationToken = optionalConfirmationToken.get();
				Optional<UserDTO> userFind = userRepository.findByEmail(confirmationToken.getUser().getEmail())
						.stream()
						.map(user -> modelMapper.map(user, UserDTO.class))
						.findFirst();

				UserDTO user = userFind.get();
				if (userFind.get() != null) {
					user.setEnabled(Boolean.TRUE);
					saveUser(user);
					
					ret = "Email verified successfully!";
				}
			}
		} catch (Exception e) {
			ret = "Error: Couldn't verify email";
		}
		
		return ret;
	}

	@Override
	@SneakyThrows
	public ResultDTO<UserDTO> saveUser(@NonNull UserDTO user) {
		ResultDTO<UserDTO> ret = new ResultDTO<UserDTO>(true);
		userRepository.save(modelMapper.map(user, User.class));
		ret.setContent(user);
		return ret;
	}

	@Override
	public ResultDTO<Void> recoverPassword(@NotEmpty String expiredToken) {
		ResultDTO<Void> ret = new ResultDTO<Void>();
		ret.setSuccessful(Boolean.TRUE);
		
		try {
			Optional<ConfirmationTokenDTO> optionalToken = confirmationTokenService.getByToken(expiredToken);
			ConfirmationTokenDTO token = optionalToken.get();
			if(token != null) {
				token.setToken(UUID.randomUUID().toString());
				confirmationTokenService.save(token);
				
				//mailService.sendVerificationToken(mailMessage);
//				mailService.sendVerificationToken(vToken.getToken(), vToken.getUser());
			}
		} catch (Exception e) {
			ret.setSuccessful(Boolean.FALSE);
		}
		
		return ret;
	}

	@Override
	public UserDTO getUserByEmail(String email) {
		return modelMapper.map(
				userRepository.findByEmail(email), 
				UserDTO.class);
		
	}
}