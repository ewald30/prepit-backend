package com.example.prepitbackend.service.dao;

import java.util.Optional;

import com.example.prepitbackend.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
    User findByUsername(String username);

    User findByEmail(String email);

    Optional<User> findById(Long id);
}
