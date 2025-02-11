package com.health_sync.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseDto {
	private int statusCode;
	private String message;
	private Object body;

	public ResponseDto(int statusCode, String message, Object body) {
		this.statusCode = statusCode;
		this.message = message;
		this.body = body;
	}

	public ResponseDto(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
}
