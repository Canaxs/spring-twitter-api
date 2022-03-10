package com.twitter.user;

import org.springframework.stereotype.Service;

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
