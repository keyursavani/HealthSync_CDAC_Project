package com.user.custome_exception;

public class HealthSynsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public HealthSynsException(String message) {
		super(message);
	}
}
