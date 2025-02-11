package com.health_sync.dto;

import com.health_sync.pojos.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterPatientDto {
	@NotBlank(message = "First name must be supplied")
	private String firstName;
	private String lastName;
	@NotBlank(message = "DateOfBirth must be supplied")
	private String dob;
	@NotNull(message = "Gender must be supplied")
	private Gender geneder;
	@NotNull(message = "Contact number must be supplied")
	private long contactNumber;
	@NotBlank(message = "Email must be supplied")
	@Email(message = "Invalid Email format")
	private String email;
	@NotBlank(message = "Address must be supplied")
	private String address;
	@NotBlank(message = "Password must be supplied")
	private String password;
}
