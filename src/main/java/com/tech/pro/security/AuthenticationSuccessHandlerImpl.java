package com.tech.pro.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.tech.pro.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Role role = User.class.cast(authentication.getPrincipal()).getRole();

		switch (role) {
			case ADMIN:
				response.sendRedirect("admin/**");
				break;
			case USER:
				response.sendRedirect("user/**");
				break;
			default:
				throw new IllegalStateException("Authentication error");
		}

	}

}
