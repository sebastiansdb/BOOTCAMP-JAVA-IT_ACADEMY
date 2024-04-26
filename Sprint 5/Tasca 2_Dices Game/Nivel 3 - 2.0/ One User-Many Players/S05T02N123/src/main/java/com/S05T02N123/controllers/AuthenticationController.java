package com.S05T02N123.controllers;

import com.S05T02N123.model.services.interfaces.AuthenticationService;
import com.S05T02N123.security.authentication.SignUpRequest;
import com.S05T02N123.security.authentication.AuthenticationResponse;
import com.S05T02N123.security.authentication.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> signUp (
            @RequestBody SignInRequest request
    ){
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> signIn (
            @RequestBody SignUpRequest request
    ){
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}
