package com.example.demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.excpetion.BadCredentialsException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Throwable.class)
	public void throwableHandler(Exception e) {
		
	}
	
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(BadCredentialsException.class)
	public void BadCredentialsExceptionHandler(BadCredentialsException e) {
		
	}

}
