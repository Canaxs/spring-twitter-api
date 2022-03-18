package com.twitter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.twitter.exception.AuthException;
import com.twitter.model.Friends;
import com.twitter.model.User;
import com.twitter.repository.FriendsRepository;
import com.twitter.repository.UserJpaRepository;
import com.twitter.request.SendIds;

@Service
public class FriendsService {


	FriendsRepository friendsRepository;
	

	UserJpaRepository userRepository;
	
	public FriendsService(FriendsRepository friendsRepository, UserJpaRepository userRepository) {
		super();
		this.friendsRepository = friendsRepository;
		this.userRepository = userRepository;
	}
	
	public void saveFriends(long id2) {
		
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(name);
		long id1 = user.getId();
			
		if(id1 != id2) {
			Friends friends = new Friends();
			User user1 = userRepository.findByid(id1);
			User user2 = userRepository.findByid(id2);
			User first = user1;
			User second = user2;
			if(user1.getId() > user2.getId()) {
				first = user2;
				second = user1;
			}
			if(!(friendsRepository.existsByFirstUserAndSecondUser(first, second))) {
				friends.setCreatedDate(new Date());
				friends.setFirstUser(first);
				friends.setSecondUser(second);
				friends.setSenderUser(id1);
				friends.setFriendAccepted(false);
				try {
				friendsRepository.save(friends);
				}
				catch(Error e) {
					throw new AuthException();
				}
			}
			else {
				throw new AuthException();
			}
		}
		
	}
	
	public void acceptFriends(SendIds id) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user1 = userRepository.findByUsername(name);
		User user2 = userRepository.findByid(id.getId2());
		User first = user1;
		User second = user2;
		
		if(user1.getId() > user2.getId()) {
			first = user2;
			second = user1;
		}
		if(friendsRepository.existsByFirstUserAndSecondUser(first, second)) {
			Friends friends = friendsRepository.findByFirstUserAndSecondUser(first,second);
			if(friends.getSenderUser() != user1.getId()) {
				friends.setFriendAccepted(true);
				friendsRepository.save(friends);
			}
			else {
				throw new AuthException();
			}
		}
		else {
			throw new AuthException();
		}
		
		
	}

	public List<User> getFriends() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(name);
		List<Friends> firstFriends = friendsRepository.findByFirstUser(user);
		List<Friends> secondFriends = friendsRepository.findBySecondUser(user);
		List<User> Friends = new ArrayList<>();
		
		for(Friends friend:firstFriends) {
			Friends.add(userRepository.findByid(friend.getSecondUser().getId()));
		}
		for(Friends friend:secondFriends) {
			Friends.add(userRepository.findByid(friend.getFirstUser().getId()));
		}
		return Friends;
	}
	
	public List<User> getUserList(String username) {
		User user = userRepository.findByUsername(username);
		List<Friends> firstFriends = friendsRepository.findByFirstUser(user);
		List<Friends> secondFriends = friendsRepository.findBySecondUser(user);
		List<User> Friends = new ArrayList<>();
		
		for(Friends friend:firstFriends) {
			Friends.add(userRepository.findByid(friend.getSecondUser().getId()));
		}
		for(Friends friend:secondFriends) {
			Friends.add(userRepository.findByid(friend.getFirstUser().getId()));
		}
		return Friends;
	}
}
