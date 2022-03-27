package com.twitter.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.twitter.model.HashTags;

@Component
public class HashTagsSpecification {

	public static Specification<HashTags> findAllByHashTag(String hashTag) {
	    return ((root,cq,cb) -> {
	    	return cb.equal(root.get("hashTag"),hashTag);
	    });
	  }
}
