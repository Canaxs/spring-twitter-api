package com.twitter.friend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.user.User;

public interface FriendsRepository extends JpaRepository<Friends, Long>{
	
	boolean existsByFirstUserAndSecondUser(User first,User second);
	
	 List<Friends> findByFirstUser(User user);
	 List<Friends> findBySecondUser(User user);
	 
	 Friends findByFirstUserAndSecondUser(User id,User id2);

}
