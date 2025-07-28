package com.gymverse.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gymverse.backend.security.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	return http
    	        .csrf(csrf -> csrf.disable()) // CSRF disabled for APIs (usually fine for JWT)
    	        .authorizeHttpRequests(auth -> auth
    	            .requestMatchers("/api/auth/**").permitAll()
    	            .anyRequest().authenticated()
    	        )
    	        .sessionManagement(session -> session
    	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	        )
    	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
    	        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}