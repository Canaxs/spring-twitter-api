package com.twitter.post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.twitter.exception.AuthException;
import com.twitter.friend.FriendsRepository;
import com.twitter.friend.FriendsService;
import com.twitter.temporary.Postİnformation;
import com.twitter.user.User;
import com.twitter.user.UserJpaRepository;

@Service
public class PostService {

	@Autowired
	PostJpaRepository postJpaRepository;
	
	@Autowired
	UserJpaRepository userJpaRepository;
	
	@Autowired
	FriendsService friendsService;

	public PostService(PostJpaRepository postJpaRepository,UserJpaRepository userJpaRepository) {
		super();
		this.postJpaRepository = postJpaRepository;
		this.userJpaRepository = userJpaRepository;
	}
	
	public User getUserİnfo() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userJpaRepository.findByUsername(username);
		return user;
	}
	
	public List<Post> getUserPosts(String username) {
		User user = userJpaRepository.findByUsername(username);
		return postJpaRepository.findByUser(user);
	}

	public Post createPost(Postİnformation postİnformation) {
		Post post = new Post();
		try {
			post.setCreateDate(new Date());
			post.setText(postİnformation.getText());
			post.setTitle(postİnformation.getTitle());
			post.setUser(getUserİnfo());
			postJpaRepository.save(post);
		}
		catch(Error e) {
			
		}
		return null;
	}

	public List<Post> getAuthPosts() {
		return postJpaRepository.findByUser(getUserİnfo());
	}

	public List<Post> getFriendsPost() {
		List<User> userList = friendsService.getFriends();
		List<Post> posts = new ArrayList();
		for(int i = 0;i<userList.size();i++) {
			List<Post> poste = new ArrayList();
			
			poste = postJpaRepository.findByUser(userList.get(i));
			
			for(int l = 0;l<poste.size();l++) {
				posts.add(poste.get(l));
			}
		}
		posts.sort(Comparator.comparing(Post::getId));
		Collections.reverse(posts);
		return posts;
	}

	public Post deletePost(long id) {
		User user = getUserİnfo();
		Post post = postJpaRepository.findByid(id);
		if(post.getUser() == user) {
			postJpaRepository.delete(post);
		}
		else {
			throw new AuthException();
		}
		return post;
	}
	
}
