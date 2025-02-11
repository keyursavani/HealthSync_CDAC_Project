package com.health_sync.security;

	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.core.userdetails.UsernameNotFoundException;
	import org.springframework.stereotype.Service;
	import org.springframework.transaction.annotation.Transactional;

import com.health_sync.dao.LoginUserDao;
import com.health_sync.pojos.LoginUser;

import lombok.AllArgsConstructor;

	@Service
	@Transactional
	@AllArgsConstructor
	public class CustomUserDetailsService implements UserDetailsService {
		private LoginUserDao userDao;

		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			LoginUser user = userDao.findByEmail(email)
					.orElseThrow(() -> new UsernameNotFoundException("Invalid email and password"));
			return new CustomUserDetails(user);
		}

}
