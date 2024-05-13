package com.springboot.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.user.payload.UserFollowersDTO;
import com.springboot.user.service.UserFollowersService;

import java.util.List;

@RestController
@RequestMapping("/api/userfollowers")
public class UserFollowersController {
    private UserFollowersService userFollowersService;
public UserFollowersController(UserFollowersService userFollowersService){
    this.userFollowersService=userFollowersService;

}


    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@RequestBody UserFollowersDTO userFollowersDTO) {
        try {
            userFollowersService.followUser(userFollowersDTO);
            return ResponseEntity.ok("User followed successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to follow user: " + e.getMessage());
        }
    }

    @DeleteMapping("/unfollow/{followerID}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long followerID) {
        try {
            userFollowersService.unfollowUser(followerID);
            return ResponseEntity.ok("User unfollowed successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to unfollow user: " + e.getMessage());
        }
    }

    @GetMapping("/followers/{userID}")
    public ResponseEntity<List<UserFollowersDTO>> getFollowers(@PathVariable Long userID) {
        try {
            List<UserFollowersDTO> followers = userFollowersService.getFollowers(userID);
            return ResponseEntity.ok(followers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}
