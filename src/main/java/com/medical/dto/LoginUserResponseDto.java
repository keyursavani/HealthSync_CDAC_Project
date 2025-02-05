package com.medical.dto;

import com.medical.entities.UseRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginUserResponseDto {
	private Long id;
	private String email;
	private String firstName;
	private UseRole role;
	private String password;
}
