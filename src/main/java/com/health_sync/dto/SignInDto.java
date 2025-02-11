package com.health_sync.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignInDto {
	
	@NotBlank(message = "Email must be supplied")
	@Email(message = "Invalid Email format")
	private String email;
	@NotBlank(message = "Password must be supplied")
	private String password;
}
