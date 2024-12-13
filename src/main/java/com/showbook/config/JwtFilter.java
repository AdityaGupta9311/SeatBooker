package com.showbook.config;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import ch.qos.logback.core.subst.Token;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
        String username = null;
        String token = null;

		String authorizationHeader = request.getHeader(JwtContsant.JWT_HEADER);
		if(authorizationHeader != null) {
			token =
		}
	}

}
