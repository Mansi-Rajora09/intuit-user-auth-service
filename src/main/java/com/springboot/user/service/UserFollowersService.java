package com.springboot.user.service;

import java.util.List;

import com.springboot.user.payload.UserFollowersDTO;

public interface UserFollowersService {

    void followUser(UserFollowersDTO userFollowersDTO);

    void unfollowUser(Long followerID);

    List<UserFollowersDTO> getFollowers(Long userID);

}
