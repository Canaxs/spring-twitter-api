package com.twitter.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.twitter.converter.CommentConverter;
import com.twitter.dto.CommentRequest;
import com.twitter.exception.AuthException;
import com.twitter.model.Comment;
import com.twitter.model.Post;
import com.twitter.model.User;
import com.twitter.repository.CommentJpaRepository;
import com.twitter.repository.PostJpaRepository;
import com.twitter.repository.UserJpaRepository;
import com.twitter.utils.Userİnfo;

@Service
public class CommentServiceImpl implements CommentService{
	
	CommentJpaRepository commentJpaRepository;
	
	UserJpaRepository userJpaRepository;
	
	PostJpaRepository postJpaRepository;
	
	CommentConverter commentConverter;
	
	Userİnfo userİnfo;

	public CommentServiceImpl(CommentJpaRepository commentJpaRepository, UserJpaRepository userJpaRepository,
			PostJpaRepository postJpaRepository,CommentConverter commentConverter,Userİnfo userİnfo) {
		super();
		this.commentJpaRepository = commentJpaRepository;
		this.userJpaRepository = userJpaRepository;
		this.commentConverter = commentConverter;
		this.postJpaRepository = postJpaRepository;
		this.userİnfo = userİnfo;
	}

	@Override
	public Comment createComment(CommentRequest comment, long postid) {
		User user = userİnfo.Auth();
		Post post = postJpaRepository.getById(postid);
		if(post != null) {
			Comment comment2 =commentConverter.getCommentConvert(comment);
			comment2.setPost(post);
			comment2.setUser(user);
			commentJpaRepository.save(comment2);
			return comment2;
		}
		else {
			throw new AuthException();
		}
	}

	@Override
	public List<Comment> getAllComments(long postid) {
		List<Comment> comment = commentJpaRepository.findAllByPostId(postid);
		return comment;
	}

	@Override
	public Comment deleteComment(long commentId) {
		Comment comment = commentJpaRepository.getById(commentId);
		if(comment != null) {
			try {
				commentJpaRepository.delete(comment);
				return comment;
			}
			catch(Error e) {
				
			}
		}
		else {
			throw new AuthException();
		}
		return null;
	}

}
