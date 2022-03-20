package com.twitter.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Token {

	@Id
	private String token;
	
	@ManyToOne
	private User user;
}
