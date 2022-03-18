package com.twitter.service;

import javax.transaction.Transactional;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.twitter.exception.AuthException;
import com.twitter.model.Token;
import com.twitter.model.User;
import com.twitter.repository.TokenRepository;
import com.twitter.repository.UserJpaRepository;
import com.twitter.request.Credentials;
import com.twitter.request.UserAuthRes;
import com.twitter.request.UserVM;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {

	UserJpaRepository userJpaRepository;
	
	TokenRepository tokenRepository;
	
	public AuthService(UserJpaRepository userJpaRepository,TokenRepository tokenRepository) {
		super();
		this.userJpaRepository = userJpaRepository;
		this.tokenRepository = tokenRepository;
	}
	
	public UserAuthRes authenticate(Credentials credentials) {
		User inDB = userJpaRepository.findByUsername(credentials.getUsername());
		if(inDB == null) {
			throw new AuthException();
		}
		if(!credentials.getPassword().equals(inDB.getPassword())) {
			throw new AuthException();
		}
		UserVM userVM = new UserVM(inDB);
		String token = Jwts.builder().setSubject(""+inDB.getId()).signWith(SignatureAlgorithm.HS512, "twitter").compact();
		
		Token tokenEntity = new Token();
		tokenEntity.setToken(token);
		tokenEntity.setUser(inDB);
		tokenRepository.save(tokenEntity);
		
		UserAuthRes response = new UserAuthRes();
		response.setUser(userVM);
		response.setToken("Bearer "+token);
		return response;
	}
	@Transactional
	public UserDetails getUserDetails(String token) {
		System.out.println("Token: "+ token);
		JwtParser parser = Jwts.parser().setSigningKey("twitter");
		try {			
			parser.parse(token);
			Claims claims = parser.parseClaimsJws(token).getBody();
			long userId = new Long(claims.getSubject());
			User user = userJpaRepository.getOne(userId);
			User actualUser = (User)((HibernateProxy)user).getHibernateLazyInitializer().getImplementation();
			return actualUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void clearToken(String token) {
		tokenRepository.deleteById(token);
		
	}
}
