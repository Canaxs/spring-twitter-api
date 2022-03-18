package com.twitter.service;

import org.springframework.stereotype.Service;

import com.twitter.repository.UserJpaRepository;
import com.twitter.repository.UserRepository;

@Service
public class UserService {
	
	UserJpaRepository userJpaRepository;
	UserRepository userRepository;
	
	public UserService(UserJpaRepository userJpaRepository, UserRepository userRepository) {
		super();
		this.userJpaRepository = userJpaRepository;
		this.userRepository = userRepository;
	}
	
	
}
