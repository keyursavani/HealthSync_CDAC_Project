package com.health_sync.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.health_sync.custome_exception.HealthSynsException;
import com.health_sync.dao.LoginUserDao;
import com.health_sync.dto.SignInDto;
import com.health_sync.dto.SignInResponseJwtDto;
import com.health_sync.dto.SignUpDto;
import com.health_sync.pojos.LoginUser;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class LoginUserServicesImpl implements LoginUserServices {

	private ModelMapper modelMapper;
	private LoginUserDao loginUserDao;
	private PasswordEncoder encoder;

	@Override
	public String signUp(SignUpDto dto) {

		if (loginUserDao.existsByEmail(dto.getEmail()))
			throw new HealthSynsException("Email already exists");

		dto.setPassword(encoder.encode(dto.getPassword()));
		LoginUser user = loginUserDao.save(modelMapper.map(dto, LoginUser.class));
		return "Regester successfully with id " + user.getId();
	}

	@Override
	public SignInResponseJwtDto signIn(SignInDto dto) {
		LoginUser user = loginUserDao.findByEmail(dto.getEmail())
				.orElseThrow(() -> new HealthSynsException("Invalid email and password"));
		if(!encoder.matches(dto.getPassword(),user.getPassword()))
			throw new HealthSynsException("Invalid email and password");
		return modelMapper.map(user, SignInResponseJwtDto.class);
	}

	@Override
	public String addLoginUser(SignInResponseJwtDto dto) {
		LoginUser user;
		if(loginUserDao.existsByEmail(dto.getEmail())) {
			user = loginUserDao.findByEmail(dto.getEmail())
					.orElseThrow(()-> new HealthSynsException("Invalid email and password"));
		}else {
			user = loginUserDao.save(modelMapper.map(dto, LoginUser.class));
		}
		return "Login user added successfully with id "+user.getId();
	}

}
