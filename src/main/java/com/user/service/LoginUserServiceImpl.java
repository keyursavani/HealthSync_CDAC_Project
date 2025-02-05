package com.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.user.custome_exception.HealthSynsException;
import com.user.dto.LoginUserResponseDto;
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

		if (loginUserRepository.existsById_email(dto.getEmail()))
			throw new HealthSynsException("Email already exists");

		dto.setPassword(encoder.encode(dto.getPassword()));
		LoginUser user = loginUserRepository.save(modelMapper.map(dto, LoginUser.class));
		return "Regester successfully with id " + user.getId();
	}

	@Override
	public SignInResponseJwtDto signIn(SignInDto dto) {
		LoginUser user = loginUserRepository.findById_email(dto.getEmail())
				.orElseThrow(() -> new HealthSynsException("Invalid email and password"));
		if(!encoder.matches(dto.getPassword(),user.getPassword()))
			throw new HealthSynsException("Invalid email and password");
		return modelMapper.map(user, SignInResponseJwtDto.class);
	}

	@Override
	public String addLoginUser(SignInResponseJwtDto dto) {
		LoginUser user;
		if(loginUserRepository.existsById_email(dto.getId().getEmail())) {
			user = loginUserRepository.findById_email(dto.getId().getEmail())
					.orElseThrow(()-> new HealthSynsException("Invalid email and password"));
		}else {
			user = loginUserRepository.save(modelMapper.map(dto, LoginUser.class));
		}
		return "Login user added successfully with id ";
	}

	@Override
	public LoginUserResponseDto findMyEmail(String emailId) {
		LoginUser user = loginUserRepository.findById_email(emailId).orElseThrow(()-> new HealthSynsException("Invalid email id"));
		return modelMapper.map(user,LoginUserResponseDto.class);
	}

	@Override
	public String userLogOut(String email) {
//		LoginUser user = loginUserRepository;
		return "You have been logged out successfully.";
	}
}
