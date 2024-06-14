package com.jdbcexercise.demo.User.Service;

import com.jdbcexercise.demo.User.Controller.UserRegistrationDto;
import com.jdbcexercise.demo.User.Controller.UserUpdateProfileDto;
import com.jdbcexercise.demo.User.Entity.UserEntity;
import com.jdbcexercise.demo.User.Repositories.UserJPARepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImplementation implements UserService {
    private final UserJPARepository userJPARepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImplementation(UserJPARepository userJPARepository, PasswordEncoder passwordEncoder) {
        this.userJPARepository = userJPARepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userJPARepository.findAll();
    }

    @Override
    public UserEntity createAccount(UserRegistrationDto req) {
        UserEntity newUser = new UserEntity();
        if(!req.getPassword().equals( req.getRetypePassword())){
            throw new IllegalArgumentException("Password do not match");
        }

        var password = passwordEncoder.encode(req.getPassword());
        newUser.setName(req.getName());
        newUser.setEmail(req.getEmail());
        newUser.setPasswordHash(password);

        return userJPARepository.save(newUser);
    }

    @Override
    public Optional<UserEntity> findProfile(Long id) {
        return userJPARepository.findById(id);
    }

    @Override
    public UserEntity updateProfile(UserUpdateProfileDto userUpdateProfileDto) {
        Optional <UserEntity> currentProfile = userJPARepository.findById(userUpdateProfileDto.getId());
        if(currentProfile.isEmpty()){
            throw new IllegalArgumentException("Your Account cannot be found");
        }
        var editedProfile = currentProfile.get();
        editedProfile.setName(userUpdateProfileDto.getName());
        editedProfile.setProfilePic(userUpdateProfileDto.getProfilePic());
        editedProfile.setEmail(userUpdateProfileDto.getEmail());

        userJPARepository.save(editedProfile);

        return editedProfile;
    }

    @Override
    public void deleteUserById (long id) {
        var existingUser = userJPARepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("Your Account Cannot Be Found");
        }
        userJPARepository.deleteById(id);

        var userFound = userJPARepository.findById(id);
        if (userFound.isPresent()){
            throw new IllegalArgumentException("Delete Action Failed");
        }
    }
}
