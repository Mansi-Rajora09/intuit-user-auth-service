package com.springboot.user.controller;

import com.springboot.user.payload.UserActivityDTO;
import com.springboot.user.service.UserActivityService;
import com.springboot.user.utils.Utility;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/intuit/api/useractivities")
public class UserActivityController {

    private UserActivityService userActivityService;
    private Utility utility;

    UserActivityController(UserActivityService userActivityService,
            Utility utility) {
        this.userActivityService = userActivityService;
        this.utility = utility;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUserActivity(@RequestBody UserActivityDTO userActivityDTO) {
        try {
            utility.validateUserActivity(userActivityDTO);
            userActivityService.addUserActivity(userActivityDTO);
            return ResponseEntity.ok("User activity added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add user activity: " + e.getMessage());
        }
    }

    @GetMapping("/{activityID}")
    public ResponseEntity<UserActivityDTO> getUserActivity(@PathVariable Long activityID) {
        try {
            UserActivityDTO userActivityDTO = userActivityService.getUserActivity(activityID);
            return ResponseEntity.ok(userActivityDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<List<UserActivityDTO>> getUserActivitiesByUser(@PathVariable Long userID) {
        try {
            List<UserActivityDTO> userActivities = userActivityService.getUserActivitiesByUser(userID);
            return ResponseEntity.ok(userActivities);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/update/{activityID}")
    public ResponseEntity<String> updateUserActivity(@PathVariable Long activityID,
            @RequestBody UserActivityDTO userActivityDTO) {
        try {
            userActivityService.updateUserActivity(activityID, userActivityDTO);
            return ResponseEntity.ok("User activity updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update user activity: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{activityID}")
    public ResponseEntity<String> deleteUserActivity(@PathVariable Long activityID) {
        try {
            userActivityService.deleteUserActivity(activityID);
            return ResponseEntity.ok("User activity deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete user activity: " + e.getMessage());
        }
    }
}