package com.medical.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.medical.dto.LoginUserResponseDto;


public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private LoginUserResponseDto user;
	public CustomUserDetails(LoginUserResponseDto entity) {
		this.user=entity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(
				user.getRole().toString()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	public String getEmail() {
		return user.getEmail();
	}
	
	public LoginUserResponseDto getUser() {
		return user;
	}
}


