
package com.medical.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.dto.LoginUserResponseDto;
import com.medical.service.clients.UserServiceClient;


import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private UserServiceClient userServiceClient;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		LoginUserResponseDto user = userServiceClient.findByEmail(email);
		return new CustomUserDetails(user);
	}

}