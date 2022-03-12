package com.twitter.friend;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.user.User;

public interface FriendsRepository extends JpaRepository<Friends, Long>{
	
	boolean existsByFirstUserAndSecondUser(User first,User second);

}
