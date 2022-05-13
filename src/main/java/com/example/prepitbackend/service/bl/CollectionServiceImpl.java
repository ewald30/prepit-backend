package com.example.prepitbackend.service.bl;

import java.util.ArrayList;

import com.example.prepitbackend.domain.Collection;
import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.service.dao.CollectionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionServiceImpl implements CollectionService{

    @Autowired
    private CollectionRepo collectionRepo;

    public ArrayList<Collection> findAll(){
        ArrayList<Collection> result = collectionRepo.findAll();
        return result;
    }

    @Override
    public ArrayList<Collection> findByUser(User user) {
        ArrayList<Collection> result = collectionRepo.findByUser(user);
        return result;
    }

    @Override
    public Collection save(Collection collection) {
        return collectionRepo.save(collection);
        
    }

}
