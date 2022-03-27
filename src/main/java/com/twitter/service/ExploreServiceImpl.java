package com.twitter.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.twitter.dto.HashTagRequest;
import static com.twitter.specification.HashTagsSpecification.*;
import com.twitter.model.HashTags;
import com.twitter.model.Post;
import com.twitter.repository.HashTagsRepository;
import com.twitter.repository.PostJpaRepository;
import com.twitter.specification.HashTagsSpecification;

@Service
public class ExploreServiceImpl implements ExploreService{
	
	PostJpaRepository postJpaRepository;
	
	HashTagsRepository hashTagsRepository;

	public ExploreServiceImpl(PostJpaRepository postJpaRepository, HashTagsRepository hashTagsRepository) {
		super();
		this.postJpaRepository = postJpaRepository;
		this.hashTagsRepository = hashTagsRepository;
	}

	@Override
	public Map<String,Integer> findAll() {
		List<HashTags> hash = hashTagsRepository.findAll();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for(HashTags s : hash) {
			if(map.get(s.getHashTag()) != null) {
				map.put(s.getHashTag(), map.get(s.getHashTag())+1);
			}
			else {
				map.put(s.getHashTag(), 1);
			}
		}
		
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.print("String: "+entry.getKey());
			System.out.println("Integer: "+entry.getValue());
		}
		
		// Written to get the largest number of hashtags
		
		Map<String,Integer> maps = map.entrySet().
        stream().
        sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
        collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));	
		
		return maps;
	}

	@Override
	public List<Post> findAllhashTagNamePosts(String hashTagName) {
		List<HashTags> posts = hashTagsRepository.findAll(findAllByHashTag(hashTagName));
		List<Post> post = new ArrayList<Post>();
		for(HashTags s : posts) {
			post.add(s.getPost());
		}
		return post;
	}
	
	

}
