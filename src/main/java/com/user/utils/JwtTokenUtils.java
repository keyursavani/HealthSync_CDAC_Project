package com.user.utils;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.user.dto.SignInDto;
import com.user.dto.SignInResponseDto;
import com.user.dto.SignInResponseJwtDto;
import com.user.security.JwtUtils;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtTokenUtils {

	private JwtUtils jwtUtils;
	private AuthenticationManager authMgr;
	private ModelMapper modelMapper;

	public SignInResponseDto generateJwtToken(SignInDto dto, SignInResponseJwtDto sdto) {
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getEmail(),
				dto.getPassword());

		Authentication verifiedToken = authMgr.authenticate(token);
		sdto.setAuthToken(jwtUtils.generateJwtToken(verifiedToken));

		SignInResponseDto ddto = modelMapper.map(sdto, SignInResponseDto.class);
		return ddto;
	}
}
