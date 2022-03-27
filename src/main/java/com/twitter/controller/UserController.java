package com.twitter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.dto.ImageRequest;
import com.twitter.dto.PasswordRequest;
import com.twitter.dto.UserRequest;
import com.twitter.model.User;
import com.twitter.service.UserService;

@RestController
@RequestMapping("/api/1.0/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping
	private ResponseEntity<String> securityName() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(name);
	}
	
	@PostMapping
	private UserRequest signUp(@RequestBody UserRequest userRequest) {
		return userService.signUp(userRequest);
	}
	
	@PostMapping("/image")
	private String imageUpdate(@RequestBody ImageRequest imageRequest) {
		userService.updateImage(imageRequest);
		return "Image saved"; 
	}
	
	@PostMapping("/password")
	private User passwordUpdate(@RequestBody PasswordRequest passwordRequest) {
		return userService.updatePassword(passwordRequest);
	}
	
}
