package com.springboot.user.repository;

import com.springboot.user.entity.UserTransactions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTransactionsRepository extends JpaRepository<UserTransactions, Long> {
}
