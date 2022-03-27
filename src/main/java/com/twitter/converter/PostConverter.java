package com.twitter.converter;

import java.util.Date;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.twitter.dto.Postİnformation;
import com.twitter.model.Post;
import com.twitter.model.User;
import com.twitter.repository.UserJpaRepository;

@Component
public class PostConverter {
	
	UserJpaRepository userJpaRepository;

	public PostConverter(UserJpaRepository userJpaRepository) {
		super();
		this.userJpaRepository = userJpaRepository;
	}

	public Post getPosts(Postİnformation postİnformation) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userJpaRepository.findByUsername(username);
		Post post = Post.builder()
				.text(postİnformation.getText())
				.title(postİnformation.getTitle())
				.user(user)
				.createDate(new Date())
				.like(0)
				.build();
		return post;
	}
}
