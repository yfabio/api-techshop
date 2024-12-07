package com.tech.pro.security;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.tech.pro.exception.ApiException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt.key}")
	private String jwtSecretKey;

	@Value("${app.jwt.expiration}")
	private int jwtExperation;

	public String generateToken(Authentication auth) {
		
		String username = auth.getName();
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MINUTE, jwtExperation);
		
		Date expireDate = c.getTime();

		String token = Jwts.builder()
						   .subject(username)
						   .issuedAt(new Date())
						   .expiration(expireDate)
						   .signWith(key())
						   .compact();

		return token;
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
	}

	
	public String getUsernameFromJwt(String token) {
		
		return Jwts.parser()
				   .verifyWith(SecretKey.class.cast(key()))
				   .build()
				   .parseSignedClaims(token)
				   .getPayload()
				   .getSubject();
								
	}
	
	public boolean validateToken(String token) {
		
		try {
			
			Jwts.parser()
			.verifyWith(SecretKey.class.cast(key()))
			.build()
			.parse(token);
			
			return true;
		}catch (MalformedJwtException malformedJwtException){
            throw new ApiException("Invalid JWT Token");
        }catch (ExpiredJwtException expiredJwtException){
            throw new ApiException( "Expired JWT token");
        }catch (UnsupportedJwtException unsupportedJwtException){
            throw new ApiException("Unsupported JWT token");
        }catch (IllegalArgumentException illegalArgumentException){
            throw new ApiException("Jwt claims string is null or empty");
        }		
		
	}
	
}
