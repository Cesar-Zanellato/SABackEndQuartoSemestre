package com.back.fortesupermercados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.fortesupermercados.dtos.token.LoginInput;
import com.back.fortesupermercados.entities.User;
import com.back.fortesupermercados.services.TokenService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private TokenService service;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public String login(@RequestBody LoginInput input){
        var user = new UsernamePasswordAuthenticationToken(
            input.getEmail(), input.getPassword()     
        );
        var auth = manager.authenticate(user);
        var token = service.createToken((User) auth.getPrincipal());
        return token;
    }
}
