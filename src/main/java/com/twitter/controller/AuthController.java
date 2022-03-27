package com.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.dto.Credentials;
import com.twitter.dto.UserAuthRes;
import com.twitter.service.AuthService;

@RestController
@RequestMapping("/api/1.0/auth")
public class AuthController {

	@Autowired
	AuthService authService;
	
	@PostMapping
	private UserAuthRes isLogged(@RequestBody Credentials credentials) {
		return authService.authenticate(credentials);
	}
	
	@PostMapping("/logout")
	String handleLogout(@RequestHeader(name = "Authorization") String authorization) {
		String token = authorization.substring(7);
		authService.clearToken(token);
		return "Logout success";
	}
}
