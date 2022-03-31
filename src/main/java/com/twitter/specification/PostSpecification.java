package com.twitter.specification;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.twitter.model.Friends;
import com.twitter.model.Post;
import com.twitter.model.User;

@Component
public class PostSpecification {

	public static Specification<Post> findByUsers(List<User> users) {
	    return ((root,cq,cb) -> {
	    	return cb.equal(root.get("user"),users);
	    });
	  }
	
}
