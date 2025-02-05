package com.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class RefreshJwtTokenDto {
	private String authToken;
}
