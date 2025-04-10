package com.site.cda_demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

  public String generateToken(AppUserDetails userDetails) {
    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .signWith(SignatureAlgorithm.HS256, "azerty")
        // .setExpiration() si on veut gérer l'expiration
        .compact();
  }

  public String getSubjectFromJwt(String jwt) {
    return Jwts.parser()
        .setSigningKey("azerty")
        .parseClaimsJws(jwt)
        .getBody()
        .getSubject();
  }
}
