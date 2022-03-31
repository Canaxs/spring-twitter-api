package com.twitter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class AuthException extends RuntimeException {

	public AuthException() {
		super();
	}
}
