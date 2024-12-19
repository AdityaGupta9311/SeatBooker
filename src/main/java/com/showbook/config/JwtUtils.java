package com.showbook.config;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.showbook.request.LoginRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private static SecretKey key = Keys.hmacShaKeyFor(JwtContsant.SECRET_KEY.getBytes());

	public static String generateToken(Map<String, Object> claims, LoginRequest loginRequest) {

		String jwt = Jwts.builder()
				.setClaims(claims)
//				.setSubject(loginRequest)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + 86400000))
				.signWith(key)
				.compact();

		return jwt;
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(key).build().parseClaimsJwt(token).getBody();
	}
	
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	public String extractRole(String token) {
		return (String) extractAllClaims(token).get("role");
	}
	
}
