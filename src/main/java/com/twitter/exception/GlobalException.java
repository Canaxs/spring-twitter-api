package com.twitter.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler({AuthException.class})
	public String authExcept() {
		return "AuthException";
	}
}
