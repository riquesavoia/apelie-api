package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.controllers.dto.user.CreateUserDto;
import com.apelie.apelieapi.models.User;
import com.apelie.apelieapi.repositories.UserRepository;
import com.apelie.apelieapi.services.FileService;
import com.apelie.apelieapi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileService fileService;

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void createUser(CreateUserDto createUserDto) {
        try {
            if (userRepository.findByEmail(createUserDto.getEmail()) != null) {
                throw new EntityExistsException("This e-mail already exists");
            }

            User newUser = new User();
            newUser.setFullName(createUserDto.getFullName());
            newUser.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
            newUser.setEmail(createUserDto.getEmail());
            try {
                newUser.setPhotoUrl(fileService.uploadFile(createUserDto.getPhoto()));
            } catch (Exception e) {
                throw new RuntimeException("Error when uploading user profile picture");
            }

            userRepository.save(newUser);
        } catch (Exception e) {
            LOGGER.error("Error when trying to create user", e);
            throw e;
        }
    }

    @Override
    public User getLoggedUser() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();

            return userRepository.findByEmail(email);
        } catch (Exception e) {
            LOGGER.error("Error when getting logged user", e);
            throw e;
        }
    }
}
