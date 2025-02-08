package com.user.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.entities.LoginUser;
import com.user.repository.LoginUserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private LoginUserRepository loginUserRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		LoginUser user = loginUserRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid email and password"));
		return new CustomUserDetails(user);
	}

}