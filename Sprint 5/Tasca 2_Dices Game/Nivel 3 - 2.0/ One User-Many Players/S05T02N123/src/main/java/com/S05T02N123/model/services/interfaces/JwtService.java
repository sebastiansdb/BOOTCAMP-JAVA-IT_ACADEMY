package com.S05T02N123.model.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUserEmail(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
}
