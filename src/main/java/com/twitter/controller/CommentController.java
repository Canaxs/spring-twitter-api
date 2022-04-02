package com.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.dto.CommentRequest;
import com.twitter.model.Comment;
import com.twitter.repository.CommentJpaRepository;
import com.twitter.service.CommentService;

@RestController
@RequestMapping("/api/1.0/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/{postId}")
	@CacheEvict(value = "getAllComments")
	public Comment createComment(@RequestBody CommentRequest commentRequest,@PathVariable long postId) {
		return commentService.createComment(commentRequest,postId);
	}
	
	@DeleteMapping("/{commentId}")
	@CacheEvict(value = "getAllComments")
	public Comment deleteComment(@PathVariable long commentId) {
		return commentService.deleteComment(commentId);
	}
	
	@GetMapping("/{postId}")
	@Cacheable(value = "getAllComments")
	public List<Comment> getAllComments(@PathVariable long postId) {
		return commentService.getAllComments(postId);
	}

}
