package com.twitter.like;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.twitter.post.Post;
import com.twitter.post.PostJpaRepository;
import com.twitter.user.User;
import com.twitter.user.UserJpaRepository;

@Service
public class LikesService {

	@Autowired
	LikesJpaRepository likesJpaRepository;
	
	@Autowired
	UserJpaRepository userJpaRepository;
	
	@Autowired
	PostJpaRepository postJpaRepository;

	public LikesService(LikesJpaRepository likesJpaRepository, UserJpaRepository userJpaRepository,
			PostJpaRepository postJpaRepository) {
		super();
		this.likesJpaRepository = likesJpaRepository;
		this.userJpaRepository = userJpaRepository;
		this.postJpaRepository = postJpaRepository;
	}
	
	public User Auth() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return userJpaRepository.findByUsername(name);
	}

	public void createLikes(int postid) {
		User user = Auth();
		if(!(likesJpaRepository.existsByUserIdAndPostId(user.getId(), postid))) {
			Post post = postJpaRepository.findByid(postid);
			try {
			Likes likes = new Likes();
			likes.setPost(post);
			likes.setUser(user);
			likesJpaRepository.save(likes);
			post.setLike(post.getLike()+1);
			postJpaRepository.save(post);
		     }
			catch(Error e) {
				
				}
			}
		}

	public boolean existLikePost(int postid) {
		User user = Auth();
		return likesJpaRepository.existsByUserIdAndPostId(user.getId(), postid);
	}

	public Likes deleteLikePost(int postid) {
		User user = Auth();
		if((likesJpaRepository.existsByUserIdAndPostId(user.getId(), postid))) {
			Likes likes = likesJpaRepository.findByUserIdAndPostId(user.getId(), postid);
			likesJpaRepository.delete(likes);
			Post post = postJpaRepository.findByid(postid);
			post.setLike(post.getLike()-1);
			postJpaRepository.save(post);
			return likes;
		}
		return null;
	}

	public List<Likes> allLikesPost(int postid) {
		List<Likes> likes  = likesJpaRepository.findAllByPostId(postid);
		return likes;
	}	
	}
