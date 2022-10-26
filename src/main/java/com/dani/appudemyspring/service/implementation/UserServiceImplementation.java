package com.dani.appudemyspring.service.implementation;

import com.dani.appudemyspring.exceptions.UserServiceException;
import com.dani.appudemyspring.io.repository.UserRepository;
import com.dani.appudemyspring.converter.UserConverter;
import com.dani.appudemyspring.io.entity.UserEntity;
import com.dani.appudemyspring.service.UserService;
import com.dani.appudemyspring.shared.dto.UserDto;
import com.dani.appudemyspring.ui.model.response.ErrorMessage;
import com.dani.appudemyspring.ui.model.response.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverted;


    @Override
    public UserEntity createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exists");

        UserEntity userEntity = userConverted.toUserEntity(user);

        return userRepository.save(userEntity);

    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return userConverted.toUserDto(userEntity);
    }

    @Override
    public UserDto getUserByUserId(long id) {

        UserEntity userEntity = userRepository.findById(id);

        if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        return userConverted.toUserDto(userEntity);
    }

    @Override
    public UserEntity updateUser(long id, UserDto user) {

        UserEntity userEntity = userRepository.findById(id);

        if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(long id) {
        UserEntity userEntity = userRepository.findById(id);
        if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userRepository.delete(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
