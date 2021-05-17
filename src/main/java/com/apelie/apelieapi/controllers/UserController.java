package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.dto.user.CreateUserDto;
import com.apelie.apelieapi.dto.user.UserResponseDto;
import com.apelie.apelieapi.mappers.UserMapper;
import com.apelie.apelieapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreateUserDto createUserRequest) {
        this.userService.createUser(createUserRequest);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserResponseDto getLoggedUser() {
        return UserMapper.toDto(this.userService.getLoggedUser());
    }
}
