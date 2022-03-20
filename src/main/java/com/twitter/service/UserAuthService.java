package com.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.twitter.model.User;
import com.twitter.repository.UserJpaRepository;
@Service
public class UserAuthService implements UserDetailsService{
	
	@Autowired
	UserJpaRepository jpaRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

			User inDB = jpaRepository.findByUsername(username);
			if(inDB == null)
				throw new UsernameNotFoundException("User not found");
			return inDB;
		}
	
	public UserDetails loadUserById(Long id) {
		User user = jpaRepository.findById(id).get();
		return user; 
	}
}

