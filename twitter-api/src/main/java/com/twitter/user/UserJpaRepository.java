package com.twitter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserJpaRepository extends JpaRepository<User,Long>{

	User findByUsername(String username);
	
	User findByid(Long id);
}
