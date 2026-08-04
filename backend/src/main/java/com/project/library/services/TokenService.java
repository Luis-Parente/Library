package com.project.library.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.library.dto.ResponseLoginDTO;
import com.project.library.exceptions.TokenException;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.expiration.minutes}")
    private long expirationMinutes;

    public ResponseLoginDTO generateToken(UserDetails user) {
        try {
            String role = user.getAuthorities().stream()
                    .findFirst()
                    .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                    .orElseThrow(() -> new TokenException("User has no role assigned!"));

            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("token-generator")
                    .withSubject(user.getUsername())
                    .withClaim("role", role)
                    .withExpiresAt(expiration())
                    .sign(algorithm);
            return new ResponseLoginDTO(token);
        } catch (JWTCreationException e) {
            throw new TokenException("Error during token generation!");
        }
    }

    public DecodedJWT decodeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("token-generator")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            throw new TokenException("Error decoding token");
        }
    }

    private Instant expiration() {
        return Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES);
    }
}
