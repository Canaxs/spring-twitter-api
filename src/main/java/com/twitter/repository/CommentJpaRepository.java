package com.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.model.Comment;

public interface CommentJpaRepository extends JpaRepository<Comment, Long>{
	
	List<Comment> findAllByPostId(long postid);
	Comment findByid(long commentId);

}
