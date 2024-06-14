package com.jdbcexercise.demo.auth.controller;


import com.jdbcexercise.demo.auth.dto.LoginRequestDto;
import com.jdbcexercise.demo.auth.dto.LoginResponseDto;
import com.jdbcexercise.demo.auth.entity.UserAuthEntity;
import com.jdbcexercise.demo.auth.service.impl.AuthService;
import com.jdbcexercise.demo.config.SecurityConfig;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
@Log
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto userLogin) throws IllegalAccessException {
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserAuthEntity userDetails = (UserAuthEntity) authentication.getPrincipal();

        log.info("Token requested for user:" + userDetails.getUsername() + "with roles" + userDetails.getAuthorities().toArray()[0]);
        String token = authService.generateToken(authentication);

        LoginResponseDto response = new LoginResponseDto();
        response.setMessage("User logged in successfully");
        response.setToken(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            authService.invalidateToken(authentication.getName());
        }
        return ResponseEntity.ok().body("User logged out successfully");

    }
}
