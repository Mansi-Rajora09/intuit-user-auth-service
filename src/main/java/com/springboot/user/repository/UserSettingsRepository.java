package com.springboot.user.repository;

import com.springboot.user.entity.UserSettings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {
}
