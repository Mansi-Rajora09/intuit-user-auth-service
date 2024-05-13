package com.springboot.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.user.payload.UserReviewDTO;
import com.springboot.user.service.UserReviewService;

@RestController
@RequestMapping("/api/userreviews")
public class UserReviewController {

    private UserReviewService userReviewService;

    public UserReviewController(UserReviewService userReviewService) {
        this.userReviewService = userReviewService;

    }

    @PostMapping("/add")
    public ResponseEntity<String> addUserReview(@RequestBody UserReviewDTO userReviewDTO) {
        try {
            userReviewService.addUserReview(userReviewDTO);
            return ResponseEntity.ok("User review added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add user review: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserReviewDTO>> getUserReviews(@PathVariable Long userId) {
        try {
            List<UserReviewDTO> userReviews = userReviewService.getUserReviews(userId);
            return ResponseEntity.ok(userReviews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/ratings/user/{userId}/{rating}")
    public ResponseEntity<List<UserReviewDTO>> getUserReviewsByRating(@PathVariable Long userId,
            @PathVariable int rating) {
        try {
            List<UserReviewDTO> userReviews = userReviewService.getUserReviewsByRating(userId, rating);
            return ResponseEntity.ok(userReviews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
