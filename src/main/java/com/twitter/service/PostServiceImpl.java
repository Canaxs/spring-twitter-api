package com.twitter.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.twitter.specification.PostSpecification.*;

import com.twitter.converter.PostConverter;
import com.twitter.dto.Postİnformation;
import com.twitter.exception.AuthException;
import com.twitter.model.HashTags;
import com.twitter.model.Post;
import com.twitter.model.User;
import com.twitter.repository.HashTagsRepository;
import com.twitter.repository.PostJpaRepository;
import com.twitter.repository.UserJpaRepository;
import com.twitter.utils.Userİnfo;

@Service
public class PostServiceImpl implements PostService{
	
	UserJpaRepository userJpaRepository;
	
	FriendsService friendsService;
	
	PostJpaRepository postJpaRepository;
	
	HashTagsRepository hashTagsRepository;
	
	PostConverter postConverter;
	
	Userİnfo userİnfo;


	public PostServiceImpl(UserJpaRepository userJpaRepository, FriendsService friendsService,
			PostJpaRepository postJpaRepository,HashTagsRepository hashTagsRepository,PostConverter postConverter,Userİnfo userİnfo) {
		super();
		this.userJpaRepository = userJpaRepository;
		this.friendsService = friendsService;
		this.postJpaRepository = postJpaRepository;
		this.postConverter = postConverter;
		this.hashTagsRepository = hashTagsRepository;
		this.userİnfo = userİnfo;
	}

	@Override
	public List<Post> getUserPosts(String username) {
		User user = userJpaRepository.findByUsername(username);
		return postJpaRepository.findByUser(user);
	}

	@Override
	public Post createPost(Postİnformation postİnformation) {
		
		try {
			Post post = postConverter.getPosts(postİnformation);
			postJpaRepository.save(post);
			String[] words = postİnformation.getText().split("#");
			List<String> hashTag = new ArrayList<String>();
			boolean firstIndex = true;
			for(String word : words) {
				if(!firstIndex) {
					String[] firstWord = word.split("\\W+");
					hashTag.add(firstWord[0]);
					HashTags hashTags = new HashTags();
					hashTags.setHashTag(firstWord[0]);
					hashTags.setPost(post);
					hashTagsRepository.save(hashTags);
					
				}
				firstIndex = false;
			}
		}
		catch(Error e) {
			
		}
		return null;
	}

	@Override
	public List<Post> getAuthPosts() {
		return postJpaRepository.findByUser(userİnfo.Auth());
	}

	@Override
	public Page<Post> getFriendsPost(Pageable page) throws IOException{
		List<Long> userList = friendsService.getLongFriends();
		List<Post> post = postJpaRepository.findByUserId(userList,page);
		Page<Post> pages = new PageImpl<Post>(post.subList(0, post.size()), page, post.size());
		return pages;
	}

	@Override
	public Post deletePost(long id) {
		User user = userİnfo.Auth();
		Post post = postJpaRepository.getById(id);
		if(post.getUser() == user) {
			postJpaRepository.delete(post);
		}
		else {
			throw new AuthException();
		}
		return post;
	}

}
