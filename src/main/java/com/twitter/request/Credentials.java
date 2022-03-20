package com.twitter.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Credentials {
	
	private String username;
	private String password;
}
