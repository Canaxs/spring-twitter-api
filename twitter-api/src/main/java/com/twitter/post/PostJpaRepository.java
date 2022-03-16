package com.twitter.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.user.User;

public interface PostJpaRepository extends JpaRepository<Post, Long>{
	
	List<Post> findByUser(User user);
	
	Post findByid(long id);

}
