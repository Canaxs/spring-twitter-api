package com.twitter.service;

import java.util.List;

import com.twitter.model.Comment;
import com.twitter.model.User;
import com.twitter.request.CommentRequest;

public interface CommentService {
	
	User Auth();
	Comment createComment(CommentRequest comment,long postid);
	List<Comment> getAllComments(long postid);
	Comment deleteComment(long commentId);

}
