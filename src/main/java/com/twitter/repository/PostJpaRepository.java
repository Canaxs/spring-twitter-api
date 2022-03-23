package com.twitter.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.model.Post;
import com.twitter.model.User;

public interface PostJpaRepository extends JpaRepository<Post, Long>{
	
	List<Post> findByUser(User user);
	
	Post findByid(long id);

}
