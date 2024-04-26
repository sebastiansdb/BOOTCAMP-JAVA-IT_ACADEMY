package com.S1T2N123.model.services.implementations;

import com.S1T2N123.model.repositories.UserRepository;
import com.S1T2N123.model.services.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findById(username)
                .orElseThrow(()-> new EntityNotFoundException("User not found."));
    }
}
