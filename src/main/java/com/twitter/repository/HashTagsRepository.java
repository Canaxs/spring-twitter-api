package com.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.model.HashTags;
import com.twitter.model.Post;

public interface HashTagsRepository extends JpaRepository<HashTags, Long>{
	
	List<HashTags> findAllByHashTag(String hashTagName);
}
