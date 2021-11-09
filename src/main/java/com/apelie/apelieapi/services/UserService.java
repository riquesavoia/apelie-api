package com.apelie.apelieapi.services;

import com.apelie.apelieapi.controllers.dto.user.CreateUserDto;
import com.apelie.apelieapi.controllers.dto.user.UpdateUserDto;
import com.apelie.apelieapi.models.User;

public interface UserService {
    /**
     * Creates a new user
     *
     * @param createUserDto
     */
    void createUser(CreateUserDto createUserDto);

    /**
     * Gets current logged user
     *
     * @return
     */
    User getLoggedUser();

    /**
     * Updates logged user info
     *
     * @return
     */
    User updateUser(UpdateUserDto updateUserDto);
}
