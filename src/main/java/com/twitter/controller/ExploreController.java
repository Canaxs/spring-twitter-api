package com.twitter.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.request.HashTagRequest;
import com.twitter.service.ExploreService;

@RestController
@RequestMapping("/api/1.0/explore")
public class ExploreController {

	@Autowired
	ExploreService exploreService;
	
	@GetMapping
	private Map<String,Integer> getHashTag() {
		return exploreService.findAll();
	}
	
}
