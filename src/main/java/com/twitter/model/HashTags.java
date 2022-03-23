package com.twitter.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Data
@Entity
public class HashTags extends BaseEntity{
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	Post post;
	
	private String hashTag;
}
