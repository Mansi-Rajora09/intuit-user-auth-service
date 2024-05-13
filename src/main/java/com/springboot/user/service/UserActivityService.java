package com.springboot.user.service;

import java.util.List;

import com.springboot.user.payload.UserActivityDTO;

public interface UserActivityService {

    void addUserActivity(UserActivityDTO userActivityDTO);

    UserActivityDTO getUserActivity(Long activityID);

    List<UserActivityDTO> getUserActivitiesByUser(Long userID);

    void updateUserActivity(Long activityID, UserActivityDTO userActivityDTO);

    void deleteUserActivity(Long activityID);

}
