package com.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.post.Post;
import com.twitter.post.PostService;
import com.twitter.temporary.Postİnformation;

@RestController
@RequestMapping("/api/1.0/post")
public class PostController {

	@Autowired
	PostService postService;
	
	@GetMapping("/{username}")
	public List<Post> getUserPosts(@PathVariable String username) {
		return postService.getUserPosts(username);
	}
	
	@GetMapping
	private List<Post> getAuthPost() {
		return postService.getAuthPosts();
	}
	
	@PostMapping
	public Post createPost(@RequestBody Postİnformation postİnformation) {
		return postService.createPost(postİnformation);
	}
	@DeleteMapping("/{id}")
	public Post deletePost(@PathVariable long id) {
		return postService.deletePost(id);
	}
	@GetMapping("/friends")
	public List<Post> getFriendsPost() {
		return postService.getFriendsPost();
	}
}
