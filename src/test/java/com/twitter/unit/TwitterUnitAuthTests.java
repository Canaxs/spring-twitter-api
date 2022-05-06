package com.twitter.unit;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.twitter.dto.Credentials;
import com.twitter.dto.UserAuthRes;
import com.twitter.dto.UserVM;
import com.twitter.model.Token;
import com.twitter.model.User;
import com.twitter.repository.TokenRepository;
import com.twitter.repository.UserJpaRepository;
import com.twitter.service.AuthService;
import com.twitter.service.AuthServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TwitterUnitAuthTests {
	
	@InjectMocks
	private AuthServiceImpl authService;
	
	@Mock
	private UserJpaRepository userJpaRepository;
	
	@Mock
	private TokenRepository tokenRepository;
	
	
	@Test
	public void Authentication() {
		Credentials credentials = new Credentials();
		credentials.setPassword("root");
		credentials.setUsername("user");
		User user = mock(User.class);
		Token token = mock(Token.class);
		UserVM uservm = mock(UserVM.class);

		Mockito.when(user.getPassword()).thenReturn(credentials.getPassword());
		Mockito.when(userJpaRepository.findByUsername(credentials.getUsername())).thenReturn(user);
		Mockito.when(tokenRepository.save(ArgumentMatchers.any(Token.class))).thenReturn(token);
		UserAuthRes authRes = authService.authenticate(credentials);

		Assertions.assertEquals(authRes.getUser().getUsername(), credentials.getUsername());
	}
}
