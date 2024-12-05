package com.tech.pro.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.tech.pro.model.User;
import com.tech.pro.service.UserService;


public class AuthenticationProviderImpl implements AuthenticationProvider {

	private UserService userService;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		if (auth != null) {

			String username = auth.getName();
			String pwd = auth.getCredentials().toString();

			User user = userService.findByEmail(username);
			
			if (userService.verify(pwd, user.getPassword())) {
				return new UsernamePasswordAuthenticationToken(username, pwd, user.getAuthorities());
			}

		}

		throw new BadCredentialsException("invalid credential");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
