package com.gymverse.backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import com.gymverse.backend.model.User;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // Ideally you should store this in application.properties or environment variables
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // secure random key
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole()) // optional
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, User user) {
        String username = extractUsername(token);
        return username.equals(user.getUsername());
    }
}