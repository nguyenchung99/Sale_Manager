package com.example.sale.reponsitory;

import com.example.sale.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface AuthenticationProvider {
    User authenticate(String username, String password) throws Exception;
}
