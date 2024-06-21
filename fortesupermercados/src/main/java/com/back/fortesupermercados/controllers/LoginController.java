package com.back.fortesupermercados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.back.fortesupermercados.dtos.token.LoginInput;
import com.back.fortesupermercados.services.TokenService;

@RestController
@Validated
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private TokenService service;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/login")
    public String login(@RequestBody LoginInput input) {
        var user = new UsernamePasswordAuthenticationToken(
                input.getEmail(), input.getPassword()
        );
        var auth = manager.authenticate(user);
        var token = service.createToken((UserDetails) auth.getPrincipal());
        return token;
    }
}
