package com.parkease.controllers;
import com.parkease.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import com.parkease.models.User;
import com.parkease.repository.UserRepository;

import javax.crypto.spec.SecretKeySpec;

@Service
public class JwtService {
    private final UserRepository userRepository;


    private final String SECRET_KEY = "your_secret_key_here";
    JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    Key key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());


    public String generateToken(User user) {

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}

