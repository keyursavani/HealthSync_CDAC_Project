package com.user.dto;


import com.user.entities.UseRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignInResponseDto {
	private Long id;
	private String email;
	private String firstName;
	private UseRole role;
	private String authToken;
}
