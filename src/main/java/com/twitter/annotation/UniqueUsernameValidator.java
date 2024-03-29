package com.twitter.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.twitter.model.User;
import com.twitter.repository.UserJpaRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{
	
	@Autowired
	UserJpaRepository repository;

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		User user = repository.findByUsername(username);
		if(user != null) {
			return false;
		}
		return true;
	}

}
