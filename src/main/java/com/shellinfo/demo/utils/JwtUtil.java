package com.shellinfo.demo.utils;

import com.shellinfo.demo.model.dto.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/*@Component
public class JwtUtil {

    private final String secret = "mysupersecuresecretkeymysupersecuresecretkey12345"; // Minimum 32 characters (256 bits)
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

    public String generateToken(String userId) {
        long milliSeconds = 86400000L; // 24 hours
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + milliSeconds))
                .signWith(key)
                .compact();
    }

    public TokenResponse generateTokens(String userId) {

        return new TokenResponse(generateAccessToken(userId), generateRefreshToken(userId));
    }

    public String generateAccessToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000)) // 15 min
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 7 days
                .signWith(key)
                .compact();
    }

    public String extractUserId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (ExpiredJwtException e) {
            return true; // already expired
        }
    }

}*/

@Component
public class JwtUtil {

    private final String secret = "mysupersecuresecretkeymysupersecuresecretkey12345";
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

    public String generateToken(String userId) {
        long milliSeconds = 86400000L; // 24 hours
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + milliSeconds))
                .signWith(key)
                .compact();
    }

    // ✅ Generate both tokens
    public TokenResponse generateTokens(String userId) {
        return new TokenResponse(
                generateAccessToken(userId),
                generateRefreshToken(userId)
        );
    }

    // ✅ Access Token (12 hours)
    public String generateAccessToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 12 * 60 * 60 * 1000))
                .signWith(key)
                .compact();
    }

    // ✅ Refresh Token (7 days)
    public String generateRefreshToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
                .signWith(key)
                .compact();
    }

    // ✅ Extract userId (subject)
    public String extractUserId(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ✅ Extract expiration
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    // ✅ Extract all claims (core method)
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ Check expired
    public boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (ExpiredJwtException e) {
            return true; // already expired
        }
    }

    // ✅ Validate token (best for filters)
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false; // expired or invalid
        }
    }
}