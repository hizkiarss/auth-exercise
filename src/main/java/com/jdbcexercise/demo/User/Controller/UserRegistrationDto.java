package com.jdbcexercise.demo.User.Controller;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String name;
    private String email;
    private String password;
    private String retypePassword;
}
