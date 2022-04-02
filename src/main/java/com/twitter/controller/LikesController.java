package com.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.model.Likes;
import com.twitter.repository.LikesJpaRepository;
import com.twitter.service.LikesService;

@RestController
@RequestMapping("/api/1.0/likes")
public class LikesController {

	@Autowired
	LikesService likesService;
	
	@PostMapping("/{postid}")
	@CacheEvict(value = "allLikesPost")
	public String likePost(@PathVariable int postid) {
		likesService.createLikes(postid);
		return "Liked";
	}
	
	@GetMapping("/{postid}")
	public boolean existLikePost(@PathVariable int postid) {
		return likesService.existLikePost(postid);
	}
	
	@DeleteMapping("/{postid}")
	@CacheEvict(value = "allLikesPost")
	public Likes deleteLikePost(@PathVariable int postid) {
		return likesService.deleteLikePost(postid);
	}
	@GetMapping("/all/{postid}")
	@Cacheable(value = "allLikesPost")
	public List<Likes> allLikesPost(@PathVariable int postid) {
		return likesService.allLikesPost(postid);
	}
}

