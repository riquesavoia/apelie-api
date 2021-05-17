package com.apelie.apelieapi.security.auth;

import com.apelie.apelieapi.models.User;
import com.apelie.apelieapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    public AuthUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username in this case will be the e-mail
        User loggedUser = userRepository.findByEmail(username);

        if (loggedUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new AuthUser(loggedUser.getEmail(), loggedUser.getPassword(), true, true, true, true);
    }
}
