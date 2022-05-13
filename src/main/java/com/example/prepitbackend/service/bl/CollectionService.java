package com.example.prepitbackend.service.bl;

import java.util.ArrayList;

import com.example.prepitbackend.domain.Collection;
import com.example.prepitbackend.domain.User;

public interface CollectionService {
    public ArrayList<Collection> findAll();
    public ArrayList<Collection> findByUser(User user);
    public Collection save(Collection collection);
}
