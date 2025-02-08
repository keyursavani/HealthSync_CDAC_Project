package com.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignoutRequestDto {
	@NotBlank(message = "Email must be supplied")
private String email;
}
