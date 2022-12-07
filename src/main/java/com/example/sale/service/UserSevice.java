package com.example.sale.service;

import com.example.sale.response.TokenResponse;
import com.example.sale.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserSevice {

    public TokenResponse resgister(User user){
        TokenResponse tokenResponse = new TokenResponse();
        return tokenResponse;
    }

}
