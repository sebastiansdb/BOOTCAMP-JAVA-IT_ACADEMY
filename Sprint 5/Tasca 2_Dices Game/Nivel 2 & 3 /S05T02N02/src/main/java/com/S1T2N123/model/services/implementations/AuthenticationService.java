package com.S1T2N123.model.services.implementations;

import com.S1T2N123.exceptions.EntityAlreadyExistsException;
import com.S1T2N123.model.dto.PlayerEntityDTO;
import com.S1T2N123.model.repositories.UserRepository;
import com.S1T2N123.security.Role;
import com.S1T2N123.security.User;
import com.S1T2N123.security.authentication.AuthenticationRequest;
import com.S1T2N123.security.authentication.AuthenticationResponse;
import com.S1T2N123.security.authentication.RegisterRequest;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        if (request.getEmail().isEmpty() || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException ("Email and password must not be null.");
        }
//        userRepository.findUserByEmail(request.getEmail())
        userRepository.findById(request.getEmail())
                .ifPresent( user -> {
                    throw new EntityExistsException("This email is already registered");
                });

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .playerEntityList(new ArrayList<>())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        // si la ejecucón llega a este punto, es porque el ususario ya está autenticado
//        User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();
        User user = userRepository.findById(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
