package com.twitter.unit;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.twitter.converter.PostConverter;
import com.twitter.model.Post;
import com.twitter.repository.HashTagsRepository;
import com.twitter.repository.PostJpaRepository;
import com.twitter.repository.UserJpaRepository;
import com.twitter.service.FriendsService;
import com.twitter.service.PostServiceImpl;
import com.twitter.utils.Userİnfo;

public class TwitterUnitPostTests {
	
	@InjectMocks
	PostServiceImpl postServiceImpl;
	
	@Mock
	UserJpaRepository userJpaRepository;
	
	@Mock
	FriendsService friendsService;
	
	@Mock
	PostJpaRepository postJpaRepository;
	
	@Mock
	HashTagsRepository hashTagsRepository;
	
	@Mock
	PostConverter postConverter;
	
	@Mock
	Userİnfo userİnfo;
	
	@Test
	public void getUserPosts() {
		String username = "user";
	}

}
