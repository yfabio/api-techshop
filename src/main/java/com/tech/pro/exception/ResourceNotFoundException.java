package com.tech.pro.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);		
	}

	public ResourceNotFoundException(String message) {
		super(message);		
	}
}
