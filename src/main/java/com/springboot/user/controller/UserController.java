package com.springboot.user.controller;

import com.springboot.user.payload.UserDetailsDTO;
import com.springboot.user.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/intuit/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/verify/{userId}")
    public ResponseEntity<String> verifyUser(@PathVariable Long userId) {
        try {
            userService.verifyUser(userId);
            return ResponseEntity.ok("User verified successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to verify user: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}/bonus")
    public ResponseEntity<Long> getBonusCount(@PathVariable Long userId) {
       Long bonus =userService.getBonus(userId);
        return ResponseEntity.ok(bonus);
    }

    @GetMapping("/{userId}/{action}")
    public ResponseEntity<String> increaseBonus(@PathVariable Long userId,@PathVariable String action) {
        try {
            userService.increaseBonus(userId,action);
            return ResponseEntity.ok("Bonus increased successfully for user with ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to increase bonus of the user: " + e.getMessage());
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUserDetails(@PathVariable Long userId,
            @RequestBody UserDetailsDTO userDetailsDTO) {
        try {
            userService.addOrUpdateUserDetails(userId, userDetailsDTO);
            return ResponseEntity.ok("User details updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update user details: " + e.getMessage());
        }
    }

    @PostMapping("/deactivate/{userId}")
    public ResponseEntity<String> deactivateAccount(@PathVariable Long userId) {
        try {
            userService.deactivateAccount(userId);
            return ResponseEntity.ok("User account deactivated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to deactivate user account: " + e.getMessage());
        }
    }

}
