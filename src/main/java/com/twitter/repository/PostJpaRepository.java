package com.twitter.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import com.twitter.model.Post;
import com.twitter.model.User;

public interface PostJpaRepository extends JpaRepository<Post, Long>{
	
	List<Post> findByUser(User user);
	
	@Query(value="FROM Post WHERE user_id IN(:userse) ORDER BY id DESC")
	List<Post> findByUserId(@Param("userse") List<Long> userid,Pageable page);

}
