package com.twitter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.temporary.Credentials;

@RestController
@RequestMapping("/api/1.0/user")
public class UserController {

	@GetMapping
	private String dee() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
}
