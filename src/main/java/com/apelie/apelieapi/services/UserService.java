package com.apelie.apelieapi.services;

import com.apelie.apelieapi.dto.user.CreateUserDto;
import com.apelie.apelieapi.models.User;

public interface UserService {
    void createUser(CreateUserDto createUserDto);

    User getLoggedUser();
}
