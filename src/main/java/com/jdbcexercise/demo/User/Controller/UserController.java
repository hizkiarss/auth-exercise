package com.jdbcexercise.demo.User.Controller;

import com.jdbcexercise.demo.User.Entity.UserEntity;
import com.jdbcexercise.demo.User.Service.UserService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@Log

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public List<UserEntity> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/profile")
    public ResponseEntity<?> register (@RequestBody UserRegistrationDto userRegistrationDto){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username =authentication.getName();
        log.info("User profile requested for user:" + username);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserEntity>> findUserProfileById(@PathVariable Long id) {
        Optional<UserEntity> user = userService.findProfile(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserRegistrationDto userDto) {
        UserEntity createdUser = userService.createAccount(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<UserEntity> updateUserProfile(@RequestBody @Valid UserUpdateProfileDto userUpdateProfileDto) {
        UserEntity updatedProfile = userService.updateProfile(userUpdateProfileDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProfile);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCart(@PathVariable long id) {
            userService.deleteUserById(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
}