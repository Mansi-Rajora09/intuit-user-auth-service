package com.springboot.user.service;

import com.springboot.user.payload.UserPreferenceDTO;

public interface UserPreferenceService {
    void updateUserPreferences(Long userId, UserPreferenceDTO userPreferenceDTO);

    UserPreferenceDTO getUserPreferences(Long userId);
}
