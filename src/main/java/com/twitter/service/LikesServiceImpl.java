package com.twitter.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.twitter.model.Likes;
import com.twitter.model.Post;
import com.twitter.model.User;
import com.twitter.repository.LikesJpaRepository;
import com.twitter.repository.PostJpaRepository;
import com.twitter.repository.UserJpaRepository;

@Service
public class LikesServiceImpl implements LikesService{
	
	LikesJpaRepository likesJpaRepository;
	
	UserJpaRepository userJpaRepository;
	
	PostJpaRepository postJpaRepository;

	public LikesServiceImpl(LikesJpaRepository likesJpaRepository, UserJpaRepository userJpaRepository,
			PostJpaRepository postJpaRepository) {
		super();
		this.likesJpaRepository = likesJpaRepository;
		this.userJpaRepository = userJpaRepository;
		this.postJpaRepository = postJpaRepository;
	}

	@Override
	public User Auth() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return userJpaRepository.findByUsername(name);
	}

	@Override
	public void createLikes(int postid) {
		User user = Auth();
		if(!(likesJpaRepository.existsByUserIdAndPostId(user.getId(), postid))) {
			Post post = postJpaRepository.getById((long) postid);
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

	@Override
	public boolean existLikePost(int postid) {
		User user = Auth();
		return likesJpaRepository.existsByUserIdAndPostId(user.getId(), postid);
	}

	@Override
	public Likes deleteLikePost(int postid) {
		User user = Auth();
		if((likesJpaRepository.existsByUserIdAndPostId(user.getId(), postid))) {
			Likes likes = likesJpaRepository.findByUserIdAndPostId(user.getId(), postid);
			likesJpaRepository.delete(likes);
			Post post = postJpaRepository.getById((long) postid);
			post.setLike(post.getLike()-1);
			postJpaRepository.save(post);
			return likes;
		}
		return null;
	}

	@Override
	public List<Likes> allLikesPost(int postid) {
		List<Likes> likes  = likesJpaRepository.findAllByPostId(postid);
		return likes;
	}

}
