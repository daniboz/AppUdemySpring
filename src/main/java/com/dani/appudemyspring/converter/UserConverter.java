package com.dani.appudemyspring.converter;

import com.dani.appudemyspring.io.entity.UserEntity;
import com.dani.appudemyspring.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserDto toUserDto (UserEntity userEntity)
    {
        var userDto = new UserDto();

        userDto.setId(userEntity.getId());

        userDto.setFirstName(userEntity.getFirstName());

        userDto.setLastName(userEntity.getLastName());

        userDto.setEmail(userEntity.getEmail());

       // userDto.setEncryptedPassword(userEntity.getEncryptedPassword());

        userDto.setEmailVerificationToken(userEntity.getEmailVerificationToken());

        userDto.setEmailVerificationStatus(userEntity.getEmailVerificationStatus());
    return userDto;
    }

    public UserEntity toUserEntity (UserDto userDto)
    {
        var userEntity = new UserEntity();

        userEntity.setId(userDto.getId());

        userEntity.setFirstName(userDto.getFirstName());

        userEntity.setLastName(userDto.getLastName());

        userEntity.setEmail(userDto.getEmail());

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        // userDto.setEncryptedPassword(userEntity.getEncryptedPassword());

        userEntity.setEmailVerificationToken(userDto.getEmailVerificationToken());

        userEntity.setEmailVerificationStatus(userDto.getEmailVerificationStatus());

        return userEntity;
    }
}
