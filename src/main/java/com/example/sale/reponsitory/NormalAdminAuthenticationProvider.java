package com.example.sale.reponsitory;

import com.example.sale.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class NormalAdminAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    UserRepository userRepository;

    @Override
    public User authenticate(String username, String password) throws Exception {
        User user = userRepository.findAllByUsername(username);

        if(Objects.nonNull(user)){
            if(BCrypt.checkpw(password, user.getPassword()))
                return user;
            else throw new Exception("Request bad password");
        }else throw new Exception("Not found User");
    }
}
