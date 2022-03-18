package com.twitter.service;

import com.twitter.model.Comment;
import com.twitter.model.User;
import com.twitter.request.CommentRequest;

public interface CommentService {
	
	User Auth();
	Comment createComment(CommentRequest comment,long postid);

}
