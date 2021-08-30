package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.dto.user.CreateUserDto;
import com.apelie.apelieapi.models.User;
import com.apelie.apelieapi.repositories.UserRepository;
import com.apelie.apelieapi.services.UserService;
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

    @Override
    public void createUser(CreateUserDto createUserDto) {
        if (userRepository.findByEmail(createUserDto.getEmail()) != null) {
            throw new EntityExistsException("This e-mail already exists");
        }

        User newUser = new User();
        newUser.setFullName(createUserDto.getFullName());
        newUser.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        newUser.setEmail(createUserDto.getEmail());

        userRepository.save(newUser);
    }

    @Override
    public User getLoggedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email);
    }
}
