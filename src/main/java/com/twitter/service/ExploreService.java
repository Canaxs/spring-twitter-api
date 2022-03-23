package com.twitter.service;

import java.util.List;
import java.util.Map;

import com.twitter.model.Post;
import com.twitter.request.HashTagRequest;


public interface ExploreService {

	Map<String,Integer> findAll();

	List<Post> findAllhashTagNamePosts(String hashTagName);
}
