package com.apelie.apelieapi.controllers.impl;

import com.apelie.apelieapi.controllers.UserController;
import com.apelie.apelieapi.dto.user.CreateUserDto;
import com.apelie.apelieapi.dto.user.UserResponseDto;
import com.apelie.apelieapi.mappers.UserMapper;
import com.apelie.apelieapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public void create(CreateUserDto createUserRequest) {
        this.userService.createUser(createUserRequest);
    }

    @Override
    public UserResponseDto getLoggedUser() {
        return UserMapper.toDto(this.userService.getLoggedUser());
    }
}