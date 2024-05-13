package com.springboot.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.user.payload.UserPreferenceDTO;
import com.springboot.user.service.UserPreferenceService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/userpreferences")
public class UserPreferenceController {

    private UserPreferenceService userPreferenceService;

    public UserPreferenceController(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }

    @PostMapping("user/{userId}")
    public ResponseEntity<String> updateUserPreferences(@PathVariable Long userId,
            @RequestBody UserPreferenceDTO userPreferenceDTO) {
        try {
            userPreferenceService.updateUserPreferences(userId, userPreferenceDTO);
            return ResponseEntity.ok("User preferences updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update user preferences: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserPreferenceDTO> getUserPreferences(@PathVariable Long userId) {

        UserPreferenceDTO userPreference = userPreferenceService.getUserPreferences(userId);
        return ResponseEntity.ok(userPreference);

    }

}
