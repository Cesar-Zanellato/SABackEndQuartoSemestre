package com.back.fortesupermercados.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.back.fortesupermercados.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private UserService userService;

    @Autowired
    private AutenticacaoEntryPoint autenticacaoEntryPoint;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager getAutheticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new ForbiddenHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request
                        -> request
                        .requestMatchers("/shopping", "/address")
                        .hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "users", "login")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "users")
                        .permitAll()
                        .requestMatchers("/delivery")
                        .permitAll()
                        .requestMatchers(
                                "/v3/api-docs/*",
                                "/v3/*",
                                "/swagger-ui.html",
                                "/swagger-ui/*"
                        )
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/category", "/subcategory", "/product")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/category", "/subcategory", "/product")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/category", "/subcategory", "/product", "/shopping", "/address")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/category", "/subcategory", "/product", "delivery")
                        .hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(
                        securityFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .exceptionHandling(handling -> {
                    handling.authenticationEntryPoint(autenticacaoEntryPoint);
                    handling.accessDeniedHandler(this.getAccessDeniedHandler());
                })
                .build();

    }
}
