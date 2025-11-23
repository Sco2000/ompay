package com.example.ompay.security;

import com.example.ompay.entity.Compte;
import com.example.ompay.service.CompteService;
import com.example.ompay.service.TokenBlacklistService;
import com.example.ompay.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CompteService compteService;
    private final TokenBlacklistService tokenBlacklistService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, CompteService compteService, TokenBlacklistService tokenBlacklistService) {
        this.jwtUtils = jwtUtils;
        this.compteService = compteService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String requestURI = request.getRequestURI();
        System.out.println("Request: " + requestURI + " - Authorization: " + authorizationHeader);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
            System.out.println("Token: " + token);

            if (tokenBlacklistService.isTokenBlacklisted(token)) {
                System.out.println("Token is blacklisted");
                // Don't authenticate if token is blacklisted
            } else if (jwtUtils.validateToken(token)) {
                String telephone = jwtUtils.extractTelephone(token);
                System.out.println("Telephone extracted: " + telephone);

                Compte compte = compteService.getCompteByTelephone(telephone).orElse(null);
                System.out.println("Compte found: " + (compte != null ? compte.getTelephone() : "null"));

                if (compte != null) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(compte, null, null);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    System.out.println("Authentication set for: " + compte.getTelephone());
                } else {
                    System.out.println("Compte not found for telephone: " + telephone);
                }
            } else {
                System.out.println("Invalid token");
            }
        } else {
            System.out.println("No Bearer token in header");
        }
        filterChain.doFilter(request, response);
    }
}
