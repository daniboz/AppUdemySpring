package com.dani.appudemyspring.ui.controller;

import com.dani.appudemyspring.converter.UserConverter;
import com.dani.appudemyspring.service.UserService;
import com.dani.appudemyspring.shared.dto.UserDto;
import com.dani.appudemyspring.ui.model.response.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @GetMapping(path="/{id}", produces = { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE })
    public UserDto getUser(@PathVariable long id)
    {

        return userService.getUserByUserId(id);
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE })
    public UserDto createUser(@RequestBody UserDto userDetails) throws Exception
    {
        if(userDetails.getFirstName().isEmpty()) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        return userConverter.toUserDto(userService.createUser(userDetails));
    }

    @PutMapping
    public String updateUser()
    {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser()
    {
        return "delete user was called";
    }
}
