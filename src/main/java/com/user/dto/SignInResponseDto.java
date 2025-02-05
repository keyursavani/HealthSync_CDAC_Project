package com.user.dto;


import com.user.entities.CompositeKey;
import com.user.entities.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignInResponseDto {
//	private Long id;
	private CompositeKey id;
//	private String email;
	private String firstName;
//	private UserRole role;
	private String authToken;
}
