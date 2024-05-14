package com.back.fortesupermercados.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    

    @Bean
    public AuthenticationManager getAutheticationManager(
        AuthenticationConfiguration config
    ) throws Exception{
        return config.getAuthenticationManager();
    }

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder builder = http.getSharedObject(
            AuthenticationManagerBuilder.class
        );
        var authentication = builder.build();

        http.cors(cors -> cors.disable())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(request ->
                request.requestMatchers(HttpMethod.POST, "/usuario")
                       .permitAll()
                       .anyRequest()
                       .authenticated()
                       )
            .authenticationManager(authentication)
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}