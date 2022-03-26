package com.twitter.converter;

import org.springframework.stereotype.Component;

import com.twitter.model.Post;
import com.twitter.request.Postİnformation;

@Component
public class PostConverter {

	public Post getPosts(Postİnformation postİnformation) {
		Post post = Post.builder()
				.text(postİnformation.getText())
				.title(postİnformation.getTitle())
				.build();
		return post;
	}
}
