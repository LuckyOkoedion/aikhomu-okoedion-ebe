package com.AikhomuLuckyOkoedion.OnlineBookStore.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtTokenProvider {

    // Secret key for signing the token (in production, we should store this securely)
    private final String SECRET_KEY = "your-secret-key";

    // Token expiration time (e.g., 1 hour)
    private final long EXPIRATION_TIME = 60 * 60 * 1000;

    // Generate a token for a userId
    public String generateToken(String userId) {
        return Jwts.builder()
            .setSubject(userId)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    // Validate the token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token); // If the token is invalid, this will throw an exception
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extract userId from the token
    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();
        return claims.getSubject(); // Returns the userId from the token
    }
}

