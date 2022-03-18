package com.twitter.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.twitter.model.User;
import com.twitter.repository.UserJpaRepository;
import com.twitter.service.AuthService;
import com.twitter.service.UserAuthService;

public class TokenFilter extends OncePerRequestFilter{
	
		@Autowired
		AuthService authService;
		
		@Autowired
		UserAuthService service;
	
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
				throws ServletException, IOException {
				String authorization = request.getHeader("Authorization");

				if(authorization != null) {
					String token = authorization.substring(7);
					
					UserDetails user = authService.getUserDetails(token);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
				
			
			filterChain.doFilter(request, response);
			
		}

}
