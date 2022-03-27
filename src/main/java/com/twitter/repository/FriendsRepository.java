package com.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.twitter.model.Friends;
import com.twitter.model.User;

public interface FriendsRepository extends JpaRepository<Friends, Long>,JpaSpecificationExecutor<Friends>{
	
	boolean existsByFirstUserAndSecondUser(User first,User second);
	 
	 Friends findByFirstUserAndSecondUser(User id,User id2);

}
