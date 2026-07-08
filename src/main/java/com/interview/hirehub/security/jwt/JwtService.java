package com.interview.hirehub.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.util.Date;
import javax.crypto.SecretKey;
import java.util.Base64;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "HireHubSuperSecretKeyForJwtAuthenticationProject2026";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours

    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes()
        );

    }

    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();

    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, String email) {

        String extractedEmail = extractEmail(token);

        return extractedEmail.equals(email)
                && !isTokenExpired(token);
    }

    private Claims extractAllClaims(String token) {

        JwtParser parser = Jwts.parser()
                .verifyWith(getSigningKey())
                .build();

        return parser
                .parseSignedClaims(token)
                .getPayload();
    }

}