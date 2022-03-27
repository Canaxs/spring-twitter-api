package com.twitter.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.twitter.model.Friends;
import com.twitter.model.User;

@Component
public class FriendsSpecification {
	
	public static Specification<Friends> findByFirstUser(User firstUser) {
	    return ((root,cq,cb) -> {
	    	return cb.equal(root.get("firstUser"),firstUser);
	    });
	  }
	
	public static Specification<Friends> findBySecondUser(User secondUser) {
	    return ((root,cq,cb) -> {
	    	return cb.equal(root.get("secondUser"),secondUser);
	    });
	  }

}
