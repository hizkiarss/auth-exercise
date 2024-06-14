package com.jdbcexercise.demo.User.Service;

import com.jdbcexercise.demo.User.Controller.UserRegistrationDto;
import com.jdbcexercise.demo.User.Controller.UserUpdateProfileDto;
import com.jdbcexercise.demo.User.Entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    List<UserEntity> findAllUsers ();
    UserEntity createAccount (UserRegistrationDto req);
    Optional<UserEntity> findProfile (Long id);
    UserEntity updateProfile (UserUpdateProfileDto userUpdateProfileDto);
    void deleteUserById (long id);
}
