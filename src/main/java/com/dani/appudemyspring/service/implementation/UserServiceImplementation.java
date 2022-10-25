package com.dani.appudemyspring.service.implementation;

import com.dani.appudemyspring.io.repository.UserRepository;
import com.dani.appudemyspring.converter.UserConverter;
import com.dani.appudemyspring.io.entity.UserEntity;
import com.dani.appudemyspring.service.UserService;
import com.dani.appudemyspring.shared.dto.UserDto;
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

        if(userRepository.findByEmail(user.getEmail())!=null) throw new RuntimeException("Record already exists");

        UserEntity userEntity= userConverted.toUserEntity(user);

        return userRepository.save(userEntity);

    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException(email);
       return userConverted.toUserDto(userEntity);
    }

    @Override
    public UserDto getUserByUserId(long id) {

      UserEntity userEntity = userRepository.findById(id);

     // if(userEntity == null) throw new UsernameNotFoundException(String.valueOf(id));

      return userConverted.toUserDto(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     UserEntity userEntity = userRepository.findByEmail(email);
      if (userEntity == null) throw new UsernameNotFoundException(email);

       return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
