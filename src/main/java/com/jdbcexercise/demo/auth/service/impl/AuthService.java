package com.jdbcexercise.demo.auth.service.impl;

import org.springframework.security.core.Authentication;

public interface AuthService {
    String generateToken (Authentication authentication);
    void invalidateToken(String email);
}