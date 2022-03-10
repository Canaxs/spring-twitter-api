package com.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.auth.AuthService;
import com.twitter.temporary.Credentials;
import com.twitter.temporary.UserAuthRes;

@RestController
@RequestMapping("/api/1.0/auth")
public class AuthController {

	@Autowired
	AuthService authService;
	
	@PostMapping
	private UserAuthRes isLogged(@RequestBody Credentials credentials) {
		return authService.authenticate(credentials);
	}
}
