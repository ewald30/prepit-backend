package com.example.prepitbackend.service.dao;

import java.util.ArrayList;
import java.util.Optional;

import com.example.prepitbackend.domain.Collection;
import com.example.prepitbackend.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepo extends JpaRepository<Collection, Long>{

    ArrayList<Collection> findAll();

    ArrayList<Collection> findByUser(User user);

    Collection save(Collection collection);
    
}
