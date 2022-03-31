package com.twitter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.twitter.specification.FriendsSpecification.*;
import com.twitter.dto.SendIds;
import com.twitter.exception.AuthException;
import com.twitter.model.Friends;
import com.twitter.model.User;
import com.twitter.repository.FriendsRepository;
import com.twitter.repository.UserJpaRepository;
import com.twitter.specification.FriendsSpecification;
import com.twitter.utils.Userİnfo;

@Service
public class FriendsServiceImpl implements FriendsService{
	
	FriendsRepository friendsRepository;
	
	UserJpaRepository userRepository;
	
	Userİnfo userİnfo;
	

	public FriendsServiceImpl(FriendsRepository friendsRepository, UserJpaRepository userRepository,Userİnfo userİnfo) {
		super();
		this.friendsRepository = friendsRepository;
		this.userRepository = userRepository;
		this.userİnfo = userİnfo;
	}
	
	@Override
	public void saveFriends(long id2) {
		User user = userİnfo.Auth();
		long id1 = user.getId();
			
		if(id1 != id2) {
			Friends friends = new Friends();
			User user1 = userRepository.getById(id1);
			User user2 = userRepository.getById(id2);
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

	@Override
	public void acceptFriends(SendIds id) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user1 = userRepository.findByUsername(name);
		User user2 = userRepository.getById(id.getId2());
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

	@Override
	public List<User> getFriends() {
		User user =  userİnfo.Auth();
		List<Friends> firstFriends = friendsRepository.findAll(findByFirstUser(user));
		List<Friends> secondFriends = friendsRepository.findAll(findBySecondUser(user));
		List<User> Friends = new ArrayList<>();
		
		for(Friends friend:firstFriends) {
			if(friend.isFriendAccepted()) {
			Friends.add(userRepository.getById(friend.getSecondUser().getId()));
			}
		}
		for(Friends friend:secondFriends) {
			if(friend.isFriendAccepted()) {
			Friends.add(userRepository.getById(friend.getFirstUser().getId()));
			}
		}
		return Friends;
	}

	@Override
	public List<User> getUserList(String username) {
		User user =  userİnfo.Auth();
		List<Friends> firstFriends = friendsRepository.findAll(findByFirstUser(user));
		List<Friends> secondFriends = friendsRepository.findAll(findBySecondUser(user));
		List<User> Friends = new ArrayList<>();
		
		for(Friends friend:firstFriends) {
			Friends.add(userRepository.getById(friend.getSecondUser().getId()));
		}
		for(Friends friend:secondFriends) {
			Friends.add(userRepository.getById(friend.getFirstUser().getId()));
		}
		return Friends;
	}

	@Override
	public void declineFriend(SendIds id2) {
		User user =  userİnfo.Auth();
		User user2 = userRepository.getById(id2.getId2());
		
		if(friendsRepository.existsByFirstUserAndSecondUser(user, user2)) {
			Friends friend = friendsRepository.findByFirstUserAndSecondUser(user, user2);
			System.out.println("friends: "+friend);
			friendsRepository.delete(friend);
		}
		else if(friendsRepository.existsByFirstUserAndSecondUser(user2, user)) {
			Friends friend = friendsRepository.findByFirstUserAndSecondUser(user2, user);
			System.out.println("friends: "+friend);
			friendsRepository.delete(friend);
		}
	}

	@Override
	public List<Long> getLongFriends() {
		User user = userİnfo.Auth();
		List<Friends> firstFriends = friendsRepository.findAll(findByFirstUser(user));
		List<Friends> secondFriends = friendsRepository.findAll(findBySecondUser(user));
		List<Long> Friends = new ArrayList<>();
		
		for(Friends friend:firstFriends) {
			if(friend.isFriendAccepted()) {
			Friends.add(friend.getSecondUser().getId());
			}
		}
		for(Friends friend:secondFriends) {
			if(friend.isFriendAccepted()) {
			Friends.add(friend.getFirstUser().getId());
			}
		}
		return Friends;
	}

}
