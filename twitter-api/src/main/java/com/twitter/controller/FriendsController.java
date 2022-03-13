package com.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.friend.FriendsService;
import com.twitter.temporary.SendIds;
import com.twitter.user.User;
import com.twitter.user.UserJpaRepository;

@RestController
@RequestMapping("/api/1.0/friends/")
public class FriendsController {
	
	@Autowired
	FriendsService friendsService;
	
	@Autowired
	UserJpaRepository userJpaRepository;
	
	@PostMapping
	private String friendRequest(@RequestBody SendIds id) {
		friendsService.saveFriends(id.getId2());
		return "Friend Request Sent";
	}
	
	@GetMapping
	private List<User> getFriends() {
		List<User> myFriends = friendsService.getFriends();
		return myFriends;
	}
	
	@GetMapping("/{username}")
	private List<User> getUserList(@RequestParam String username) {
		List<User> myFriends = friendsService.getUserList(username);
		return myFriends;
	}
	
	@PostMapping("/accept")
	private String friendAccept(@RequestBody SendIds id2) {
		friendsService.acceptFriends(id2);
		return "Friend Accepted";
	}

}
