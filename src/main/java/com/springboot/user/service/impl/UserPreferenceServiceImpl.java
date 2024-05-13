package com.springboot.user.service.impl;

import com.springboot.user.entity.User;
import com.springboot.user.entity.UserPreference;
import com.springboot.user.payload.UserPreferenceDTO;
import com.springboot.user.repository.UserPreferenceRepository;
import com.springboot.user.repository.UserRepository;
import com.springboot.user.service.UserPreferenceService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {

        private UserRepository userRepository;

        private UserPreferenceRepository userPreferenceRepository;

        private ModelMapper modelMapper;

        public UserPreferenceServiceImpl(UserRepository userRepository,
                        UserPreferenceRepository userPreferenceRepository,
                        ModelMapper modelMapper) {
                this.userRepository = userRepository;
                this.userPreferenceRepository = userPreferenceRepository;
                this.modelMapper = modelMapper;

        }

        @Override
        public void updateUserPreferences(Long userId, UserPreferenceDTO userPreferenceDTO) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

                // Check if user already has preferences, if not, create a new UserPreference
                // entity
                UserPreference userPreference = userPreferenceRepository.findByUser(user)
                                .orElse(new UserPreference());

                // Update user preferences based on userPreferenceDTO
                userPreference.setUser(user);
                userPreference.setLanguage(userPreferenceDTO.getLanguage());
                userPreference.setNotificationSetting(userPreferenceDTO.getNotificationSetting());

                // Save or update user preference entity
                userPreferenceRepository.save(userPreference);
        }

        @Override
        public UserPreferenceDTO getUserPreferences(Long userId) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

                // Check if user already has preferences, if not, create a new UserPreference
                // entity
                UserPreference userPreference = userPreferenceRepository.findByUser(user)
                                .orElse(new UserPreference());
                return modelMapper.map(userPreference, UserPreferenceDTO.class);
        }
}