package com.parkease.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkease.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}

