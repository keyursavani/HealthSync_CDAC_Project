package com.health_sync.dto;

import com.health_sync.pojos.UseRole;

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
	private UseRole role;
	private String password;
	private String authToken;
}
