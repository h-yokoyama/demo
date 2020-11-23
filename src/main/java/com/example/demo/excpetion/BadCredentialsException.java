package com.example.demo.excpetion;

import lombok.Getter;

@Getter
public class BadCredentialsException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;
	
	public BadCredentialsException(String message) {
		this.message = message;
	}
}
