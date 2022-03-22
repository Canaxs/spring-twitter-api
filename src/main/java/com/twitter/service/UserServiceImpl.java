package com.twitter.service;

import javax.transaction.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.twitter.exception.AuthException;
import com.twitter.model.User;
import com.twitter.repository.UserJpaRepository;
import com.twitter.request.ImageRequest;
import com.twitter.request.PasswordRequest;
import com.twitter.request.UserRequest;

@Service
public class UserServiceImpl implements UserService{
	
	UserJpaRepository userJpaRepository;
	

	public UserServiceImpl(UserJpaRepository userJpaRepository) {
		super();
		this.userJpaRepository = userJpaRepository;
	}

	@Override
	public UserRequest signUp(UserRequest userRequest) {
		User user = new User();
		if(userRequest != null) {
			user.setPassword(userRequest.getPassword());
			user.setUsername(userRequest.getUsername());
			userJpaRepository.save(user);
		}
		return userRequest;
	}

	@Override
	public void updateImage(ImageRequest imageRequest) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userJpaRepository.findByUsername(name);
		if(imageRequest.getImage() != null) {
			user.setImage(imageRequest.getImage());
			userJpaRepository.save(user);
		}
		else {
				throw new AuthException();
		}
		
	}

	@Override
	public User updatePassword(PasswordRequest passwordRequest) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userJpaRepository.findByUsername(name);
		if(passwordRequest.getPassword() != null) {
			user.setPassword(passwordRequest.getPassword());
			return userJpaRepository.save(user);
		}
		else {
			throw new AuthException();
		}
		
	}

}
