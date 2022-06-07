package com.example.prepitbackend.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenDTO {
    private String jwtToken;
    private String refreshToken;
    
}
