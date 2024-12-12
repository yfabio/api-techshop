package com.tech.pro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ConfigSecurity {

	@Bean
	AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new AuthenticationSuccessHandlerImpl();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> {
				csrf.disable();				
			}).cors(c -> c.disable())
			.exceptionHandling(c -> {
				c.authenticationEntryPoint(jwtAuthenticationEntryPoint);
			})
			.sessionManagement(c -> {
				c.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			})
			.authorizeHttpRequests(c -> {
				c.requestMatchers("/js/**", "/css/**", "/public/**").permitAll();
				c.requestMatchers("/api/products/**", "/api/techshop/**", "/h2-console/**").permitAll();
				c.requestMatchers("/api/auth/**").permitAll();			
				c.requestMatchers("/api/admin/**")
				 .hasAnyAuthority(Role.ADMIN.getRole())
				 .requestMatchers("/api/user/**")
				 .hasAnyAuthority(Role.USER.getRole())
				 .anyRequest().authenticated();
		}).headers(header -> {
			header.frameOptions(op -> op.sameOrigin());
		});

		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

}
