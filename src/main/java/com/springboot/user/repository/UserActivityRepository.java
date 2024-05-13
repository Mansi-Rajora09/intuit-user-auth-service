package com.springboot.user.repository;

import com.springboot.user.entity.UserActivity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    List<UserActivity> findByUserId(Long userID);
}
