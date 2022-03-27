package com.twitter.service;

import javax.transaction.Transactional;

import com.twitter.dto.ImageRequest;
import com.twitter.dto.PasswordRequest;
import com.twitter.dto.UserRequest;
import com.twitter.model.User;

public interface UserService {

	UserRequest signUp(UserRequest userRequest);

	void updateImage(ImageRequest imageRequest);

	@Transactional
	User updatePassword(PasswordRequest passwordRequest);
}
