package com.twitter.service;

import java.util.List;

import com.twitter.model.Post;
import com.twitter.model.User;
import com.twitter.request.Postİnformation;

public interface PostService {
	
	User getUserİnfo();
	List<Post> getUserPosts(String username);
	Post createPost(Postİnformation postİnformation);
	List<Post> getAuthPosts();
	List<Post> getFriendsPost();
	Post deletePost(long id);

}
