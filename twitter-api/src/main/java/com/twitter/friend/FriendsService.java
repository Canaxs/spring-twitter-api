package com.twitter.friend;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.user.User;
import com.twitter.user.UserJpaRepository;

@Service
public class FriendsService {

	@Autowired
	FriendsRepository friendsRepository;
	
	@Autowired
	UserJpaRepository userRepository;
	
	public void saveFriends(long id1 ,long id2) {
			
		Friends friends = new Friends();
		
		User user1 = userRepository.findByid(id1);
		User user2 = userRepository.findByid(id2);
		User first = user1;
		User second = user2;
		if(user1.getId() > user2.getId()) {
			first = user2;
			second = user1;
		}
		if(!(friendsRepository.existsByFirstUserAndSecondUser(user1, user2))) {
			friends.setCreatedDate(new Date());
			friends.setFirstUser(user1);
			friends.setSecondUser(user2);
			friendsRepository.save(friends);
		}
		
	}
}
