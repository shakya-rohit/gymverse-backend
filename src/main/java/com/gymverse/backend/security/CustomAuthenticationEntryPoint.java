package com.gymverse.backend.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String json = String.format(
            "{" +
                "\"timestamp\": \"%s\", " +
                "\"status\": 401, " +
                "\"error\": \"Unauthorized\", " +
                "\"message\": \"%s\", " +
                "\"path\": \"%s\"" +
            "}",
            LocalDateTime.now(),
            authException.getMessage(),
            request.getRequestURI()
        );

        response.getWriter().write(json);
    }
}