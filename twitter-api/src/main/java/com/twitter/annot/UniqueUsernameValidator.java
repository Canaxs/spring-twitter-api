package com.twitter.annot;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.twitter.user.User;
import com.twitter.user.UserJpaRepository;

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
