package com.example.sale.reponsitory;

import com.example.sale.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findAllByUsername(String username);
    Boolean existsAllByUsername(String username);
    Boolean existsAllByEmail(String email);

}
