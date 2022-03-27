package com.twitter.dto;

import lombok.Data;

@Data
public class UserAuthRes {

	private String token;
	
	private UserVM user;
}
