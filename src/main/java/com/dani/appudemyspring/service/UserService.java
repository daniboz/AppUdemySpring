package com.dani.appudemyspring.service;

import com.dani.appudemyspring.io.entity.UserEntity;
import com.dani.appudemyspring.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    UserEntity createUser(UserDto user);
    UserDto getUser(String email);
    UserDto getUserByUserId(long id);



}
