package com.user.dto;

import com.user.entities.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class SignInResponseJwtDto {
	private Long id;
	private String email;
	private String firstName;
	private UserRole role;
	private String password;
	private String authToken;
}
