package com.health_sync.dto;

import com.health_sync.pojos.UseRole;

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
	private UseRole role;
	@NotBlank(message = "Password must be supplied")
	private String password;
}
