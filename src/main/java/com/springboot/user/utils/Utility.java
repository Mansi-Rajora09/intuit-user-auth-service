package com.springboot.user.utils;

import java.util.Collection;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.springboot.user.payload.RegisterDto;
import com.springboot.user.payload.UserActivityDTO;

@Service
public class Utility {

     // Regular expression patterns for email and password validation
     private static final String EMAIL_PATTERN = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
     private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
 
     private static final Pattern EMAIL_REGEX = Pattern.compile(EMAIL_PATTERN);
     private static final Pattern PASSWORD_REGEX = Pattern.compile(PASSWORD_PATTERN);
 
      public void validateUserRegister(RegisterDto registerDto) {
       // Perform validation checks
       if (registerDto == null) {
        throw new IllegalArgumentException("RegisterDto cannot be null");
    }
    if (isNullOrEmpty(registerDto.getName())) {
        throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (isNullOrEmpty(registerDto.getUsername())) {
        throw new IllegalArgumentException("Username cannot be null or empty");
    }
    if (isNullOrEmpty(registerDto.getEmail()) || !EMAIL_REGEX.matcher(registerDto.getEmail()).matches()) {
        throw new IllegalArgumentException("Invalid email format");
    }
    
    if (isNullOrEmpty(registerDto.getPassword())  || !PASSWORD_REGEX.matcher(registerDto.getPassword()).matches()) {
        throw new IllegalArgumentException("Password must be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one digit, and one special character");
    }
      }

      public void validateUserActivity(UserActivityDTO userActivityDTO) {
        // Perform validation checks
        
     if (isNullOrEmpty(userActivityDTO.getActivityDescription()) ) {
        throw new IllegalArgumentException("userActivity Description cannot be null");
    }
    if (isNullOrEmpty(userActivityDTO .getActivityType())) {
        throw new IllegalArgumentException("userActivity Type cannot be null");
    }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
