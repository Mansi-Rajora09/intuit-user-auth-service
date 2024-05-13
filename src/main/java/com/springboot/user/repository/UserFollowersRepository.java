package com.springboot.user.repository;

import com.springboot.user.entity.UserFollowers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowersRepository extends JpaRepository<UserFollowers, Long> {

    List<UserFollowers> findByFollowedUserId(Long userID);
}
