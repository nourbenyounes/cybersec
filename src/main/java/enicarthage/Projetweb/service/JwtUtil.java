package enicarthage.Projetweb.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import enicarthage.Projetweb.entity.Personne;


@Component
public class JwtUtil {

    @Value("${security.jwt.secret-key}")
    private String secret;
    @Value("${security.jwt.token-expiration-time}")
    private int tokenExpirationTime;


    public String generateToken(Personne personne, String role) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + tokenExpirationTime * 10000); 

        return Jwts.builder()
                .setSubject(personne.getEmail())
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public String extractRole(String token) {
        Claims claims = extractClaims(token);
        return (String) claims.get("role");
    }

    public String extractNom(String token) {
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
