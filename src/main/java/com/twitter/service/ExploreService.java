package com.twitter.service;

import java.util.List;
import java.util.Map;

import com.twitter.dto.HashTagRequest;
import com.twitter.model.Post;


public interface ExploreService {

	Map<String,Integer> findAll();

	List<Post> findAllhashTagNamePosts(String hashTagName);
}
