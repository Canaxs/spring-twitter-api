package com.twitter.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.twitter.dto.Postİnformation;
import com.twitter.model.Post;
import com.twitter.model.User;

public interface PostService {
	
	List<Post> getUserPosts(String username);
	Post createPost(Postİnformation postİnformation);
	List<Post> getAuthPosts();
	Page<Post> getFriendsPost(Pageable page) throws IOException;
	Post deletePost(long id);

}
