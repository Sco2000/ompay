package com.example.ompay.security;

import com.example.ompay.entity.Compte;
import com.example.ompay.service.CompteService;
import com.example.ompay.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CompteService compteService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, CompteService compteService) {
        this.jwtUtils = jwtUtils;
        this.compteService = compteService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies)
                    .filter(c -> "access_token".equals(c.getName()))
                    .findFirst()
                    .ifPresent(cookie -> {
                        String token = cookie.getValue();
                        if (jwtUtils.validateToken(token)) {
                            String telephone = jwtUtils.extractTelephone(token);
                            Compte compte = compteService.getCompteByTelephone(telephone).orElse(null);

                            if (compte != null) {
                                UsernamePasswordAuthenticationToken auth =
                                        new UsernamePasswordAuthenticationToken(compte, null, null);
                                SecurityContextHolder.getContext().setAuthentication(auth);
                            }
                        }
                    });
        }
        filterChain.doFilter(request, response);
    }
}
