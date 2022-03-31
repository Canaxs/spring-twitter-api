package com.twitter.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.twitter.model.User;
import com.twitter.repository.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Userİnfo {
	
	private final UserJpaRepository userRepository;

	public User Auth() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(name);
		return user;
	}
}
