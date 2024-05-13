package com.springboot.user.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import com.springboot.user.entity.UserActivity;
import com.springboot.user.payload.UserActivityDTO;
import com.springboot.user.repository.UserActivityRepository;
import com.springboot.user.repository.UserRepository;
import com.springboot.user.service.UserActivityService;

@Service
public class UserActivityServiceImpl implements UserActivityService {

    private UserActivityRepository userActivityRepository;

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserActivityServiceImpl(UserActivityRepository userActivityRepository,
            UserRepository userRepository, ModelMapper modelMapper) {
        this.userActivityRepository = userActivityRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public void addUserActivity(UserActivityDTO userActivityDTO) {
        UserActivity userActivity = new UserActivity();
        userActivity.setUser(userRepository.findById(userActivityDTO.getUserId())
                .orElseThrow(
                        () -> new IllegalArgumentException("User not found with id: " + userActivityDTO.getUserId())));
        userActivity.setActivityType(userActivityDTO.getActivityType());
        userActivity.setActivityDescription(userActivityDTO.getActivityDescription());
        userActivity.setActivityDate(new Date());
        userActivityRepository.save(userActivity);
    }

    @Override
    public UserActivityDTO getUserActivity(Long activityID) {
        UserActivity userActivity = userActivityRepository.findById(activityID)
                .orElseThrow(() -> new IllegalArgumentException("User activity not found with id: " + activityID));
        return modelMapper.map(userActivity, UserActivityDTO.class);

    }

    @Override
    public List<UserActivityDTO> getUserActivitiesByUser(Long userID) {
        List<UserActivity> userActivities = userActivityRepository.findByUserId(userID);

        List<UserActivityDTO> response = userActivities.stream()
                .map((userActivity) -> modelMapper.map(userActivity, UserActivityDTO.class))
                .collect(Collectors.toList());
        return response;

    }

    @Override
    public void updateUserActivity(Long activityID, UserActivityDTO userActivityDTO) {
        UserActivity userActivity = userActivityRepository.findById(activityID)
                .orElseThrow(() -> new IllegalArgumentException("User activity not found with id: " + activityID));
        // Update user activity properties based on the DTO
        userActivity.setActivityType(userActivityDTO.getActivityType());
        userActivity.setActivityDescription(userActivityDTO.getActivityDescription());
        userActivity.setActivityDate(new Date());
        userActivityRepository.save(userActivity);
    }

    @Override
    public void deleteUserActivity(Long activityID) {
        userActivityRepository.deleteById(activityID);
    }

}