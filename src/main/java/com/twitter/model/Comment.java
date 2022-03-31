package com.twitter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name="post_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	Post post;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	User user;
	
	private String text;
}
