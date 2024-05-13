package com.springboot.user.repository;

import com.springboot.user.entity.User;
import com.springboot.user.entity.UserPreference;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {

    Optional<UserPreference> findByUser(User user);

}
