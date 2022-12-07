package com.example.sale.controller;

import com.example.sale.response.TokenResponse;
import com.example.sale.entity.User;
import com.example.sale.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class UserController {

    @Autowired
    UserSevice userSevice;

    @PostMapping
    public TokenResponse register(@RequestBody User user){
        return userSevice.resgister(user);
    }

}
