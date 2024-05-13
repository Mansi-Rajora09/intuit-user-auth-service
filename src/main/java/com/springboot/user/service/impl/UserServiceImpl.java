package com.springboot.user.service.impl;

import com.springboot.user.entity.User;
import com.springboot.user.payload.UserDetailsDTO;
import com.springboot.user.repository.UserRepository;
import com.springboot.user.service.UserService;
import com.springboot.user.utils.VerificationStatus;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServiceImpl(
            UserRepository userRepository,
            ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper=modelMapper;

    }

    @Override
    public void verifyUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        user.setVerificationStatus(VerificationStatus.VERIFIED);
        userRepository.save(user);
    }

    @Override
    public void deactivateAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        user.setIsActive(false);
        // Perform any other necessary actions for deactivation
        userRepository.save(user);
    }


    @Override
    public void addOrUpdateUserDetails(Long userId, UserDetailsDTO userDetailsDTO) {
        // Validate and set user details
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        if (isValid(userDetailsDTO.getFullName())) {
            user.setFullName(userDetailsDTO.getFullName());
        }
        if (userDetailsDTO.getDateOfBirth() != null) {
            user.setDateOfBirth(userDetailsDTO.getDateOfBirth());
        }
        if (isValid(userDetailsDTO.getGender())) {
            user.setGender(userDetailsDTO.getGender());
        }
        if (isValid(userDetailsDTO.getAddress())) {
            user.setAddress(userDetailsDTO.getAddress());
        }
        if (isValid(userDetailsDTO.getPhoneNumber())) {
            user.setPhoneNumber(userDetailsDTO.getPhoneNumber());
        }
        if (isValid(userDetailsDTO.getProfilePicture())) {
            user.setProfilePicture(userDetailsDTO.getProfilePicture());
        }
        if (isValid(userDetailsDTO.getBiography())) {
            user.setBiography(userDetailsDTO.getBiography());
        }
        if (isValid(userDetailsDTO.getLocation())) {
            user.setLocation(userDetailsDTO.getLocation());
        }

        // Save updated user entity
        userRepository.save(user);
    }
    private boolean isValid(String value) {
        return value != null && !value.isEmpty();
    }
}
