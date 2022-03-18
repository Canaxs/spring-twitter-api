package com.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.model.Comment;
import com.twitter.repository.CommentJpaRepository;
import com.twitter.request.CommentRequest;
import com.twitter.service.CommentService;

@RestController
@RequestMapping("/api/1.0/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/{postid}")
	public Comment createComment(@RequestBody CommentRequest commentRequest,@PathVariable long postid) {
		return commentService.createComment(commentRequest,postid);
	}

}
