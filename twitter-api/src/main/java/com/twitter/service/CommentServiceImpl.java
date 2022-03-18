package com.twitter.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.twitter.model.Comment;
import com.twitter.model.Post;
import com.twitter.model.User;
import com.twitter.repository.CommentJpaRepository;
import com.twitter.repository.PostJpaRepository;
import com.twitter.repository.UserJpaRepository;
import com.twitter.request.CommentRequest;

@Service
public class CommentServiceImpl implements CommentService{
	
	CommentJpaRepository commentJpaRepository;
	
	UserJpaRepository userJpaRepository;
	
	PostJpaRepository postJpaRepository;

	public CommentServiceImpl(CommentJpaRepository commentJpaRepository, UserJpaRepository userJpaRepository,
			PostJpaRepository postJpaRepository) {
		super();
		this.commentJpaRepository = commentJpaRepository;
		this.userJpaRepository = userJpaRepository;
		this.postJpaRepository = postJpaRepository;
	}

	@Override
	public User Auth() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return userJpaRepository.findByUsername(name);
	}

	@Override
	public Comment createComment(CommentRequest comment, long postid) {
		User user = Auth();
		Post post = postJpaRepository.findByid(postid);
		if(post != null) {
			Comment comment2 = new Comment();
			comment2.setPost(post);
			comment2.setUser(user);
			comment2.setText(comment.getText());
			commentJpaRepository.save(comment2);
			return comment2;
		}
		return null;
	}

}
