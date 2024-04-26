package com.S05T02N123.model.services.interfaces;

import com.S05T02N123.security.authentication.SignUpRequest;
import com.S05T02N123.security.authentication.AuthenticationResponse;
import com.S05T02N123.security.authentication.SignInRequest;

public interface AuthenticationService {
    AuthenticationResponse signUp (SignInRequest request);
    AuthenticationResponse signIn (SignUpRequest request);
}
