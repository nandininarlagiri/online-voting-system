package com.onlinevoting.online_voting_system.security;

// import java.security.Key;
import javax.crypto.SecretKey;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
// import io.jsonwebtoken.security.SignatureAlgorithm;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

@Component
public class JwtUtil {

     @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String email) {

        // Key key = Keys.hmacShaKeyFor(secret.getBytes());
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(key, Jwts.SIG.HS256)
            .compact();
    }

    public String extractEmail(String token) {

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        Claims claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {

    try {

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);

        return true;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}
