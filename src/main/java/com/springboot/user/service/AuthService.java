package com.springboot.user.service;

import com.springboot.user.payload.LoginDto;
import com.springboot.user.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
