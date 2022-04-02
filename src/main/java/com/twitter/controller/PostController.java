package com.twitter.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.dto.Postİnformation;
import com.twitter.model.Post;
import com.twitter.service.PostService;

@RestController
@RequestMapping("/api/1.0/post")
public class PostController {

	@Autowired
	PostService postService;
	
	@Cacheable(value = "getUserPosts")
	@GetMapping("/{username}")
	public List<Post> getUserPosts(@PathVariable String username) {
		return postService.getUserPosts(username);
	}
	
	@Cacheable(value = "getAuthPost")
	@GetMapping
	private List<Post> getAuthPost() {
		return postService.getAuthPosts();
	}
	
	@PostMapping
	@CacheEvict(value = "getAuthPost")
	public Post createPost(@RequestBody Postİnformation postİnformation) {
		return postService.createPost(postİnformation);
	}
	@DeleteMapping("/{id}")
	@CacheEvict(value = "getAuthPost")
	public Post deletePost(@PathVariable long id) {
		return postService.deletePost(id);
	}
	
	@Cacheable(value = "getFriendsPost")
	@GetMapping("/friends")
	public Page<Post> getFriendsPost(Pageable page) throws IOException {
		return postService.getFriendsPost(page);
	}
}
