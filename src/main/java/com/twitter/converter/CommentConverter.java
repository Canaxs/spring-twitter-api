package com.twitter.converter;

import org.springframework.stereotype.Component;

import com.twitter.dto.CommentRequest;
import com.twitter.model.Comment;

@Component
public class CommentConverter {

	public Comment getCommentConvert(CommentRequest commentRequest) {
		Comment comment = Comment.builder()
				.text(commentRequest.getText())
				.build();
		return comment;
	}
}
