package com.dev.project.service;

import com.dev.project.dto.DataTO;
import com.dev.project.dto.UserTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String key;

    public String toJWT(UserTO userTO) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextTime = now.plus(1, ChronoUnit.WEEKS);
        return Jwts.builder()
                .setSubject(userTO.toString())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now.toInstant(ZoneOffset.of("-03:00"))))
                .setExpiration(Date.from(nextTime.toInstant(ZoneOffset.of("-03:00"))))
                .claim("name", "project-fiscal")
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(key.getBytes()))
                .compact();
    }

    public Jws<Claims> parseJWT(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Base64.getEncoder().encodeToString(key.getBytes()))
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
