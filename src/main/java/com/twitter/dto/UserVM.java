package com.twitter.dto;

import com.twitter.model.User;

import lombok.Data;

@Data
public class UserVM {

	private String username;
	
	private String image;
	
	public UserVM(User user) {
		this.setUsername(user.getUsername());
		this.setImage(user.getImage());
	}
}
