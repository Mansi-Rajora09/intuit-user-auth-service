package com.springboot.user.service;

import java.util.List;

import com.springboot.user.payload.UserReviewDTO;

public interface UserReviewService {

    void addUserReview(UserReviewDTO userReviewDTO);

    List<UserReviewDTO> getUserReviews(Long userId);

    List<UserReviewDTO> getUserReviewsByRating(Long userId,int rating);

}
