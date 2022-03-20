package com.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.model.Friends;
import com.twitter.model.User;

public interface FriendsRepository extends JpaRepository<Friends, Long>{
	
	boolean existsByFirstUserAndSecondUser(User first,User second);
	
	 List<Friends> findByFirstUser(User user);
	 List<Friends> findBySecondUser(User user);
	 
	 Friends findByFirstUserAndSecondUser(User id,User id2);

}
