package com.jdbcexercise.demo.User.Controller;

import lombok.Data;

@Data
public class UserUpdateProfileDto {
    private Long id;
    private String name;
    private String email;
    private String profilePic;
}
