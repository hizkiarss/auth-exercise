package com.jdbcexercise.demo.auth.service.impl;

import com.jdbcexercise.demo.User.Repositories.UserJPARepository;
import com.jdbcexercise.demo.auth.entity.UserAuthEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    private final UserJPARepository userJPARepository;

    public UserDetailServiceImpl(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    @Override
    public UserAuthEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        var userData =userJPARepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return new UserAuthEntity(userData);
    }
}