package com.user.dto;


import com.user.entities.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignUpDto {

	@NotBlank(message = "Email must be supplied")
	@Email(message = "Invalid Email format")
	private String email;
	@NotBlank(message = "Name must be supplied")
	private String firstName;
	@NotNull(message = "Role must be supplied")
	private UserRole role;
	@NotBlank(message = "Password must be supplied")
	private String password;
}
