package com.challenge.simpleApi.shared.security.JWT;

import com.challenge.simpleApi.domains.users.models.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JWTService {

  @Value("${security.jwt.expiracao}")
  private String expiracao;

  @Value("${security.jwt.chaveassinatura}")
  private String chaveAssinatura;

  public String GenerateToken(Users user) {
    long expString = Long.valueOf(expiracao);
    LocalDateTime datahoraExpiracao = LocalDateTime.now().plusMinutes(expString);
    Instant instant = datahoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
    Date data = Date.from(instant);

    HashMap<String, Object> claims = new HashMap<>();
    claims.put("roles", user.isAdmin() ? "ADMIN" : "USER");

    return Jwts.builder()
      .setSubject(user.getUsername())
      .setExpiration(data)
      //.setClaims(claims)
      .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
      .compact();
  }

  private Claims getClaims(String token) throws ExpiredJwtException {
    return Jwts.parser()
      .setSigningKey(chaveAssinatura)
      .parseClaimsJws(token)
      .getBody();
  }

  public boolean ValidateToken(String token) {
    try {
      Claims claims = getClaims(token);
      Date dataExpiracao = claims.getExpiration();
      LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
      boolean isValid = !LocalDateTime.now().isAfter(data);
      return isValid;
    } catch (Exception e) {
      return false;
    }
  }

  public String getLogin(String token) throws ExpiredJwtException {
    return (String) getClaims(token).getSubject();
  }
}
