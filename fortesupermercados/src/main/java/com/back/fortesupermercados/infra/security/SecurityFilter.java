package com.back.fortesupermercados.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.back.fortesupermercados.entities.User;
import com.back.fortesupermercados.repositories.UserRepository;
import com.back.fortesupermercados.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService service;

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, 
            HttpServletResponse response, 
            FilterChain filterChain
    ) throws ServletException, IOException { 
            
        var token = getToken(request);
        if (token != null) {
            Long userId = service.validToken(token);
            User user = repository.findById(userId).orElse(null);
            
            if (user != null) {
                var auth = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
    
    private String getToken(HttpServletRequest request){
        var auth = request.getHeader("Authorization");
        if (auth != null) {
            return auth.replace("Bearer", "").trim();
        }
        return null;
    }
}