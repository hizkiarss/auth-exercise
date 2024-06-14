package com.jdbcexercise.demo.auth.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class AuthRedisRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public AuthRedisRepository (RedisTemplate<String, String> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public void saveJwtToken(String token, String authentication) {
        redisTemplate.opsForValue().set(authentication, token, 10, TimeUnit.HOURS);

    }

    public String findTokenByEmail(String email){
        return redisTemplate.keys("*").stream()
                .filter(key -> email.equals(redisTemplate.opsForValue().get(key)))
                .findFirst()
                .orElse(null);
    }

    public void deleteJwtToken(String token){
        redisTemplate.delete(token);
    }

    public String getJwtToken (String email){
        return redisTemplate.opsForValue().get(email);
    }
}
