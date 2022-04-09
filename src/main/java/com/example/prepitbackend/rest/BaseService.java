package com.example.prepitbackend.rest;
/**
 *  Base class for the other rest services. It holds the references to the other services
 *  and provides access to methods for rendering responses
 */

 import org.springframework.http.ResponseEntity;

public class BaseService {

    protected ResponseEntity<Object> renderResponse(Object object){
        return ResponseEntity.ok(object);
    }
    
}