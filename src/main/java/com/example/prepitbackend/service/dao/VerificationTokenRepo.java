package com.example.prepitbackend.service.dao;

import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.domain.VerificationToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
