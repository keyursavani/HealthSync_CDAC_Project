package com.health_sync.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.health_sync.pojos.LoginUser;

import io.jsonwebtoken.Claims;


public class CustomUserDetails implements UserDetails {
	private LoginUser user;
	
	public CustomUserDetails(LoginUser entity) {
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
	
	public LoginUser getUser() {
		return user;
	}
}

