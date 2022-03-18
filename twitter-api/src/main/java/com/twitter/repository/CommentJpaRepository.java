package com.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.model.Comment;

public interface CommentJpaRepository extends JpaRepository<Comment, Long>{

}
