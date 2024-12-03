package com.example.mysandbox.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        log.debug("Processing request to '{}' with auth header: {}",
                request.getRequestURI(),
                authHeader != null ? "present" : "absent");

        if (shouldNotFilter(authHeader)) {
            log.debug("No authentication header found or invalid format");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwt = authHeader.substring(7);
            log.debug("JWT token extracted from header");

            String username = jwtUtil.extractUsername(jwt);
            log.debug("Username extracted from token: {}", username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                log.debug("Validating token for user: {}", username);

                if (jwtUtil.isTokenValid(jwt)) {
                    log.debug("Token is valid, loading user details");
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authToken = createAuthenticationToken(userDetails, request);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.debug("Authentication successful for user: {}", username);
                } else {
                    log.warn("Token validation failed for user: {}", username);
                }
            }
        } catch (Exception e) {
            log.error("Authentication failed: {}", e.getMessage());
            sendErrorResponse(response, "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean shouldNotFilter(String authHeader) {
        boolean shouldNotFilter = authHeader == null || !authHeader.startsWith("Bearer ");
        if (shouldNotFilter) {
            log.debug("Request should not be filtered: {}",
                    authHeader == null ? "header missing" : "invalid format");
        }
        return shouldNotFilter;
    }

    private UsernamePasswordAuthenticationToken createAuthenticationToken(
            UserDetails userDetails,
            HttpServletRequest request
    ) {
        log.debug("Creating authentication token for user: {}", userDetails.getUsername());

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        log.debug("Authentication token created with authorities: {}",
                userDetails.getAuthorities());
        return authToken;
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        log.warn("Sending error response: {}", message);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        String jsonResponse = String.format("{\"error\": \"%s\"}", message);
        response.getWriter().write(jsonResponse);
    }
}