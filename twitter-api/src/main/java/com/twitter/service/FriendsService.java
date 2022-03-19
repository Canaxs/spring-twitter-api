package com.twitter.service;

import java.util.List;

import com.twitter.model.User;
import com.twitter.request.SendIds;

public interface FriendsService {

	User Auth();
	void saveFriends(long id2);
	void acceptFriends(SendIds id);
	List<User> getFriends();
	List<User> getUserList(String username);
	void declineFriend(SendIds id2);
	
}
