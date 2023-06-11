package com.js.springbootcrmbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwTProvider {
    private final JWT jwt;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    public String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        return generateTokenWithUsername(principal.getUsername());
    }

    public String generateTokenWithUsername(String username){
        Algorithm algorithm = Algorithm.HMAC256("baeldung");
        var builder = JWT.create()
                .withIssuer("self")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusMillis(jwtExpirationInMillis))
                .withSubject(username)
                .withClaim("scope", "ROLE_USER");
        return builder.sign(algorithm);
    }

    public Long getJwtExpirationInMillis(){
        return jwtExpirationInMillis;
    }
}
