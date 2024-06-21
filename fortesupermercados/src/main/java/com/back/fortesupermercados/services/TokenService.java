package com.back.fortesupermercados.services;

import java.time.Instant;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.back.fortesupermercados.infra.security.CustomUserDetails;

@Service
public class TokenService {

    private String secret = "SECRET_TOKEN";
    private Integer expiration = 30;
    private String issuer = "Forte Supermercados";

    public String createToken(UserDetails userDetails) {
        var algoritmo = Algorithm.HMAC256(secret);
        CustomUserDetails customUser = (CustomUserDetails) userDetails;
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(userDetails.getUsername())
                .withClaim("id", customUser.getId())
                .withExpiresAt(getExpiration())
                .sign(algoritmo);
    }

    private Instant getExpiration() {
        return Instant.now().plusSeconds(expiration * 60);
    }

    public Long validToken(String jwt) {
        var algorithm = Algorithm.HMAC256(secret);
        var verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();

        var decodedJWT = verifier.verify(jwt);
        return decodedJWT.getClaim("id").asLong(); // Recupera o ID do usuário
    }

}
