package com.back.fortesupermercados.services;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.back.fortesupermercados.entities.User;

@Service
public class TokenService {
    private String secret = "SECRET_TOKEN";
    private Integer expiration = 30;
    private String issuer = "Forte Supermercados"; 

    public String createToken(User user) {
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(user.getUsername())
                .withExpiresAt(getExpiration())
                .sign(algoritmo);
    }

    private Instant getExpiration() {
        return Instant.now().plusSeconds(expiration * 60);
    }

    public String validToken(String jwt) {
        var algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(jwt)
                .getSubject();
    }

}
