package com.twitter.auth;

import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.twitter.exception.AuthException;
import com.twitter.temporary.Credentials;
import com.twitter.temporary.UserAuthRes;
import com.twitter.temporary.UserVM;
import com.twitter.user.User;
import com.twitter.user.UserJpaRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class AuthService {

	UserJpaRepository userJpaRepository;
	
	public AuthService(UserJpaRepository userJpaRepository) {
		super();
		this.userJpaRepository = userJpaRepository;
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
		
		UserAuthRes response = new UserAuthRes();
		response.setUser(userVM);
		response.setToken(token);
		return response;
	}
	
	@Transactional
	public Long getUserDetails(String token) {
		Claims claims = Jwts.parser().setSigningKey("twitter").parseClaimsJws(token).getBody();
		try {			
			Long userId = Long.parseLong(claims.getSubject());
			System.out.println(userId);
			 // User user = userJpaRepository.findByid(userId);
			// User actualUser = (User)((HibernateProxy)user).getHibernateLazyInitializer().getImplementation();
			return userId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey("twitter").parseClaimsJws(token);
			return !isTokenExpired(token);
		} catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
	}
	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser().setSigningKey("twitter").parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}
	Long getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey("twitter").parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
