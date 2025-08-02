package com.gymverse.backend.security;

import com.gymverse.backend.repository.UserRepository;

import io.jsonwebtoken.JwtException;

import com.gymverse.backend.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter  {
	
	Logger logger = LoggerFactory.getLogger(JwtFilter.class);

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain) throws ServletException, IOException {
	    String authHeader = request.getHeader("Authorization");

	    if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        String token = authHeader.substring(7);
	        try {
	            String username = jwtService.extractUsername(token);

	            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	                User user = userRepository.findByUsername(username).orElse(null);

	                if (user != null && jwtService.validateToken(token, user)) {
	                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
	                            user.getUsername(), null, null);
	                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                    SecurityContextHolder.getContext().setAuthentication(auth);
	                }
	            }
	        } catch (JwtException | IllegalArgumentException ex) {
	            logger.error("Invalid or malformed JWT: {}", ex.getMessage());
	            throw new BadCredentialsException("Invalid JWT token", ex);
	        }
	    }

	    filterChain.doFilter(request, response);
	}
}