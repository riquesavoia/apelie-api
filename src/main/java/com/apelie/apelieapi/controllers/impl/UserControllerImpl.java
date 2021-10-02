package com.apelie.apelieapi.controllers.impl;

import com.apelie.apelieapi.controllers.UserController;
import com.apelie.apelieapi.controllers.dto.user.CreateUserDto;
import com.apelie.apelieapi.controllers.dto.user.UserResponseDto;
import com.apelie.apelieapi.mappers.UserMapper;
import com.apelie.apelieapi.models.Store;
import com.apelie.apelieapi.models.User;
import com.apelie.apelieapi.services.StoreService;
import com.apelie.apelieapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @Override
    public void create(CreateUserDto createUserRequest) {
        this.userService.createUser(createUserRequest);
    }

    @Override
    public UserResponseDto getLoggedUser() {
        User loggedUser = this.userService.getLoggedUser();
        boolean hasStore = this.storeService.storeExistsByUserId(loggedUser.getUserId());

        return UserMapper.toDto(loggedUser, hasStore);
    }
}
