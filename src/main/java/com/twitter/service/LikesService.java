package com.twitter.service;

import java.util.List;

import com.twitter.model.Likes;
import com.twitter.model.User;

public interface LikesService {

	void createLikes(int postid);
	boolean existLikePost(int postid);
	Likes deleteLikePost(int postid);
	List<Likes> allLikesPost(int postid);
}
