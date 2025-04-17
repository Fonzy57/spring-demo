package com.site.cda_demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SecurityUtils {

  @Value("${jwt.secret}")
  private String jwtSecret;

  public String getRole(AppUserDetails userDetails) {
    return userDetails.getAuthorities()
        .stream()
        .map(r -> r.getAuthority())
        .findFirst()
        .orElse(null);
  }

  public String generateToken(AppUserDetails userDetails) {
    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .addClaims(Map.of("role", getRole(userDetails)))
        .signWith(SignatureAlgorithm.HS256, this.jwtSecret)
        // .setExpiration() si on veut gérer l'expiration
        .compact();
  }

  public String getSubjectFromJwt(String jwt) {
    return Jwts.parser()
        .setSigningKey(this.jwtSecret)
        .parseClaimsJws(jwt)
        .getBody()
        .getSubject();
  }
}
