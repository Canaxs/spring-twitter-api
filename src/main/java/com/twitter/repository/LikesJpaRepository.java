package com.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.model.Likes;

public interface LikesJpaRepository extends JpaRepository<Likes, Long>{

	boolean existsByUserIdAndPostId(long user,long post);
	
	Likes findByUserIdAndPostId(long user,long post);
	
	List<Likes> findAllByPostId(long post);
}
