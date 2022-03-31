package com.twitter.service;

import java.util.List;

import com.twitter.dto.SendIds;
import com.twitter.model.User;

public interface FriendsService {
	
	void saveFriends(long id2);
	void acceptFriends(SendIds id);
	List<User> getFriends();
	List<Long> getLongFriends();
	List<User> getUserList(String username);
	void declineFriend(SendIds id2);
	
}
