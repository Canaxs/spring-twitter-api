package com.twitter.service;

import javax.transaction.Transactional;

import com.twitter.model.User;
import com.twitter.request.ImageRequest;
import com.twitter.request.PasswordRequest;
import com.twitter.request.UserRequest;

public interface UserService {

	UserRequest signUp(UserRequest userRequest);

	void updateImage(ImageRequest imageRequest);

	@Transactional
	User updatePassword(PasswordRequest passwordRequest);
}
