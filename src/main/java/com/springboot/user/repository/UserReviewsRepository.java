package com.springboot.user.repository;

import com.springboot.user.entity.UserReviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReviewsRepository extends JpaRepository<UserReviews, Long> {

    List<UserReviews> findByUserId(Long userId);

    List<UserReviews> findByUserIdAndRating(Long userId, int rating);
}
