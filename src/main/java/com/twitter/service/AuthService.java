package com.twitter.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.twitter.dto.Credentials;
import com.twitter.dto.UserAuthRes;

public interface AuthService {

	UserAuthRes authenticate(Credentials credentials);
	UserDetails getUserDetails(String token);
	void clearToken(String token);
}
