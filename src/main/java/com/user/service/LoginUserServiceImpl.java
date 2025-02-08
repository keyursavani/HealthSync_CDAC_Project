package com.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.custome_exception.HealthSynsException;
import com.user.dto.LoginUserResponseDto;
import com.user.dto.SignoutRequestDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseJwtDto;
import com.user.dto.SignUpDto;
import com.user.entities.LoginUser;
import com.user.repository.LoginUserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class LoginUserServiceImpl implements LoginUserService {

	private ModelMapper modelMapper;
	private LoginUserRepository loginUserRepository;
	private PasswordEncoder encoder;

	@Override
	public String signUp(SignUpDto dto) {

		if (loginUserRepository.existsByEmail(dto.getEmail()))
			throw new HealthSynsException("This email is already registered. Please use a different email.");

		dto.setPassword(encoder.encode(dto.getPassword()));
		LoginUser user = loginUserRepository.save(modelMapper.map(dto, LoginUser.class));
		return  "Welcome! You have been successfully registered. Your ID: " + user.getId();
	}

	@Override
	public SignInResponseJwtDto signIn(SignInDto dto) {
		LoginUser user = loginUserRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new HealthSynsException("Invalid email or password. Please try again"));
		if (!encoder.matches(dto.getPassword(), user.getPassword()))
			throw new HealthSynsException("Invalid email or password. Please try again");
		return modelMapper.map(user, SignInResponseJwtDto.class);
	}

	@Override
	public String addLoginUser(SignInResponseJwtDto dto) {
		LoginUser user = modelMapper.map(dto, LoginUser.class);
		user.setId(null);
		if (loginUserRepository.existsByEmail(dto.getEmail())) {
			user = loginUserRepository.findByEmail(dto.getEmail())
					.orElseThrow(() -> new HealthSynsException("Invalid email or password. Please try again"));
		} else {
			user = loginUserRepository.save(user);
		}
		return "User successfully logged in with ID: " + user.getId();
	}

	@Override
	public LoginUserResponseDto findMyEmail(String emailId) {
		LoginUser user = loginUserRepository.findByEmail(emailId)
				.orElseThrow(() -> new HealthSynsException("Invalid email. Please try again"));
		return modelMapper.map(user, LoginUserResponseDto.class);
	}

	@Override
	public String userSignOut(SignoutRequestDto dto) {
		LoginUser user = loginUserRepository.findByEmail(dto.getEmail()).orElseThrow(()-> new HealthSynsException("You are already logged out."));
		loginUserRepository.deleteById(user.getId());
		return "You have been logged out successfully.";
	}
}