package com.twitter.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.twitter.request.Credentials;
import com.twitter.request.UserAuthRes;

public interface AuthService {

	UserAuthRes authenticate(Credentials credentials);
	UserDetails getUserDetails(String token);
	void clearToken(String token);
}
