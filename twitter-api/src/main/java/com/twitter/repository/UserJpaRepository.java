package com.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.twitter.model.User;

public interface UserJpaRepository extends JpaRepository<User,Long>{

	User findByUsername(String username);
	
	User findByid(Long id);
}
