package com.medical.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

	private CustomJwtAuthenticationFilter jwtFilter;

	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
		http.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(request -> request
						.requestMatchers("/medicalrecord/image/{name}","/v*/api-doc*/**", "/swagger-ui/**")
						.permitAll().requestMatchers(HttpMethod.OPTIONS).permitAll()
						.requestMatchers("/medicalrecord/patient/{patientId}")
						.hasAuthority("PATIENT")
						.requestMatchers("/insuranceplan/add")
						.hasAuthority("INSURANE_PROVIDER")
						.requestMatchers("/medicalrecord/add","/medicalrecord/delete/{recordId}", "/medicalrecord/doctor/{doctorId}","/medicalrecord/update/{recordId}","/medicalrecord/{recordId}")
						.hasAuthority("DOCTOR").anyRequest().authenticated())
//				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
