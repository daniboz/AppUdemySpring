package com.dani.appudemyspring.ui.controller;

import com.dani.appudemyspring.converter.UserConverter;
import com.dani.appudemyspring.exceptions.UserServiceException;
import com.dani.appudemyspring.service.UserService;
import com.dani.appudemyspring.shared.dto.UserDto;
import com.dani.appudemyspring.ui.model.response.ErrorMessages;
import com.dani.appudemyspring.ui.model.response.OperationStatusModel;
import com.dani.appudemyspring.ui.model.response.RequestOperationName;
import com.dani.appudemyspring.ui.model.response.RequestOperationStatus;
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
        if(userDetails.getFirstName().isEmpty()) throw new NullPointerException("The object is null");
        return userConverter.toUserDto(userService.createUser(userDetails));
    }

    @PutMapping(
            path="/{id}",
            consumes = { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE })
    public UserDto updateUser(@PathVariable long id, @RequestBody UserDto userDetails)
    {
        if(userDetails.getFirstName().isEmpty()) throw new NullPointerException("The object is null");
        return userConverter.toUserDto(userService.updateUser(id, userDetails));
    }

    @DeleteMapping( path="/{id}",produces = { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE })
    public OperationStatusModel deleteUser(@PathVariable long id)
    {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());
        userService.deleteUser(id);
        returnValue.setOperationResult(RequestOperationStatus.SUCCES.name());
        return returnValue;
    }
}
