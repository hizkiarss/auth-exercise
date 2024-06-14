package com.jdbcexercise.demo.auth.service;

import com.jdbcexercise.demo.User.Repositories.UserJPARepository;
import com.jdbcexercise.demo.auth.repository.AuthRedisRepository;
import com.jdbcexercise.demo.auth.service.impl.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;
import lombok.extern.java.Log;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;
    private final UserJPARepository userJPARepository;
    private final AuthRedisRepository authRedisRepository;


    public AuthServiceImpl(JwtEncoder jwtEncoder, AuthRedisRepository authRedisRepository, PasswordEncoder passwordEncoder, UserJPARepository userJPARepository, RedisTemplate<String, String> redisTemplate) {
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
        this.userJPARepository = userJPARepository;
        this.authRedisRepository = authRedisRepository;

    }

    @Override
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(""));

        var existingKey = authRedisRepository.getJwtToken(authentication.getName());
        if (existingKey != null) {
            log.info("Token already exists.");
            return existingKey;
        }

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        authRedisRepository.saveJwtToken(token, authentication.getName());
        return token;

    }

    public void invalidateToken(String email) {
        String tokenFoundByEmail = authRedisRepository.findTokenByEmail(email);
        if (tokenFoundByEmail != null) {
            authRedisRepository.deleteJwtToken(tokenFoundByEmail);
        }

    }
}