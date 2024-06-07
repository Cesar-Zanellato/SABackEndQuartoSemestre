package com.back.fortesupermercados.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.back.fortesupermercados.services.UserService;

import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;
    
    @Autowired
    private UserService userService;
    
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager getAutheticationManager(
        AuthenticationConfiguration config
    ) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder builder = http.getSharedObject(
            AuthenticationManagerBuilder.class
        );
        builder.userDetailsService(userService).passwordEncoder(getPasswordEncoder());
        var authentication = builder.build();

        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(request ->
                request
                       .requestMatchers("/users")
                       .hasRole("USER")

                       .requestMatchers(HttpMethod.POST, "/login")
                       .permitAll()
                       .requestMatchers(HttpMethod.POST, "/category")
                       .hasRole("ADMIN")
                       .requestMatchers(HttpMethod.PUT, "/category")
                       .hasRole("ADMIN")
                       .requestMatchers(HttpMethod.GET, "/category")
                       .permitAll()
                       .requestMatchers(HttpMethod.DELETE, "/category")
                       .hasRole("ADMIN")

                       .requestMatchers(HttpMethod.POST, "/subcategory")
                       .hasRole("ADMIN")
                       .requestMatchers(HttpMethod.PUT, "/subcategory")
                       .hasRole("ADMIN")
                       .requestMatchers(HttpMethod.GET, "/subcategory")
                       .permitAll()
                       .requestMatchers(HttpMethod.DELETE, "/subcategory")
                       .hasRole("ADMIN")

                       .requestMatchers(HttpMethod.POST, "/delivery")
                       .permitAll()
                       .requestMatchers(HttpMethod.PUT, "/delivery")
                       .permitAll()
                       .requestMatchers(HttpMethod.GET, "/delivery")
                       .permitAll()
                       .requestMatchers(HttpMethod.DELETE, "/delivery")
                       .permitAll()

                       .requestMatchers(HttpMethod.POST, "/product")
                       .hasRole("ADMIN")
                       .requestMatchers(HttpMethod.PUT, "/product")
                       .hasRole("ADMIN")
                       .requestMatchers(HttpMethod.GET, "/product")
                       .permitAll()
                       .requestMatchers(HttpMethod.DELETE, "/product")
                       .hasRole("ADMIN")

                       .requestMatchers(HttpMethod.POST, "/shopping")
                       .hasRole("USER")
                       .requestMatchers(HttpMethod.PUT, "/shopping")
                       .hasRole("USER")
                       .requestMatchers(HttpMethod.GET, "/shopping")
                       .permitAll()
                       .requestMatchers(HttpMethod.DELETE, "/shopping")
                       .hasRole("USER")

                       .requestMatchers(HttpMethod.POST, "/address")
                       .hasRole("USER")
                       .requestMatchers(HttpMethod.PUT, "/address")
                       .hasRole("USER")
                       .requestMatchers(HttpMethod.GET, "/address")
                       .permitAll()
                       .requestMatchers(HttpMethod.DELETE, "/address")
                       .hasRole("USER")

                       .anyRequest()
                       .authenticated()
                       )
            .authenticationManager(authentication)
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}