package com.twitter.like;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesJpaRepository extends JpaRepository<Likes, Long>{

	boolean existsByUserIdAndPostId(long user,long post);
	
	Likes findByUserIdAndPostId(long user,long post);
	
	List<Likes> findAllByPostId(long post);
}
