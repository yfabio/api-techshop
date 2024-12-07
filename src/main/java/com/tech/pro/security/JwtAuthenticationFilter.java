package com.tech.pro.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		/*
		 * get JWT token from http request
		 * validate token
		 * get username from token.
		 * load user associated with token
		 * set spring security
		 * 
		 */
		
		
		
		Optional<String> op = getJwtFromRequest(request);
		
		if(op.isPresent()) {
			
			String token = op.get();
			
			if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
				
				String userName = jwtTokenProvider.getUsernameFromJwt(token);
				
				UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
				
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
			}
		}
				
		filterChain.doFilter(request, response);
		
	}
	
	// Bearer <accesstoken>
	private Optional<String> getJwtFromRequest(HttpServletRequest req) {
		
		String bearerToken = req.getHeader("Authorization");
		
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
			return Optional.of(bearerToken.substring(7,bearerToken.length()));
		}
		
		return Optional.empty();
		
	}
	
	
	
	
	

}
