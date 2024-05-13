package com.springboot.user.service.impl;

import com.springboot.user.entity.User;
import com.springboot.user.entity.UserReviews;
import com.springboot.user.payload.UserReviewDTO;
import com.springboot.user.repository.UserRepository;
import com.springboot.user.repository.UserReviewsRepository;
import com.springboot.user.service.UserReviewService;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

@Service
public class UserReviewServiceImpl implements UserReviewService {

    private UserRepository userRepository;

    private UserReviewsRepository userReviewRepository;
    private ModelMapper modelMapper;

    public UserReviewServiceImpl(UserRepository userRepository,
            UserReviewsRepository userReviewRepository,
            ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userReviewRepository = userReviewRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public void addUserReview(UserReviewDTO userReviewDTO) {
        // Convert UserReviewDTO to UserReview entity and save it to the database
        User user = userRepository.findById(userReviewDTO.getUserId())
                .orElseThrow(
                        () -> new IllegalArgumentException("User not found with id: " + userReviewDTO.getUserId()));
        User targetUser = userRepository.findById(userReviewDTO.getUserId())
                        .orElseThrow(
                                () -> new IllegalArgumentException("User not found with id: " + userReviewDTO.getUserId()));
        UserReviews userReview = new UserReviews();
        userReview.setUser(user);
        userReview.setRating(userReviewDTO.getRating());
        userReview.setReviewText(userReviewDTO.getReviewText());
        userReview.setTargetUser(targetUser);

        double totalRating = getAverageRating(userReviewDTO.getUserId());
        user.setRatings(totalRating);
        userRepository.save(user);
        // Save the user review entity
        userReviewRepository.save(userReview);
    }

    @Override
    public List<UserReviewDTO> getUserReviews(Long userId) {
        // Retrieve user reviews from the database based on userId and convert them to
        // DTOs
        List<UserReviews> userReviews = userReviewRepository.findByUserId(userId);

        List<UserReviewDTO> response = userReviews.stream()
                .map((userReview) -> modelMapper.map(userReview, UserReviewDTO.class))
                .collect(Collectors.toList());
        return response;
    }

    public double getAverageRating(Long userId) {
        // Calculate the average rating for the user based on their reviews
        List<UserReviews> userReviews = userReviewRepository.findByUserId(userId);
        if (userReviews.isEmpty()) {
            return 0.0;
        }
        double totalRating = userReviews.stream()
                .mapToInt(UserReviews::getRating)
                .sum();
        return totalRating / userReviews.size();
    }

    @Override
    public List<UserReviewDTO> getUserReviewsByRating(Long userId,int rating) {
        // Retrieve user reviews from the database based on rating and convert them to
        // DTOs
        List<UserReviews> userReviews = userReviewRepository.findByUserIdAndRating(userId,rating);
        List<UserReviewDTO> response = userReviews.stream()
                .map((userReview) -> modelMapper.map(userReview, UserReviewDTO.class))
                .collect(Collectors.toList());
        return response;
    }

}
