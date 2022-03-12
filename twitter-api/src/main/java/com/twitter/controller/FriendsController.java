package com.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.friend.FriendsService;
import com.twitter.temporary.SendIds;
import com.twitter.user.User;
import com.twitter.user.UserJpaRepository;

@RestController
@RequestMapping("/api/1.0/friends")
public class FriendsController {
	
	@Autowired
	FriendsService friendsService;
	
	@Autowired
	UserJpaRepository userJpaRepository;
	
	@PostMapping
	private String deneme(@RequestBody SendIds id) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userJpaRepository.findByUsername(name);
		System.out.println(user.getId());
		friendsService.saveFriends(user.getId(), id.getId2());
		return "Oldu";
	}

}
