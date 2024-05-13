package com.springboot.user.controller;

import com.springboot.user.payload.JWTAuthResponse;
import com.springboot.user.payload.LoginDto;
import com.springboot.user.payload.RegisterDto;
import com.springboot.user.service.AuthService;
import com.springboot.user.utils.Utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;
    private Utility utility;

    public AuthController(AuthService authService,
            Utility utility) {
        this.authService = authService;
        this.utility = utility;

    }

    // Build Login REST API
    @PostMapping(value = { "/login", "/signin" })
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping(value = { "/register", "/signup" })
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        utility.validateUserRegister(registerDto);
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}