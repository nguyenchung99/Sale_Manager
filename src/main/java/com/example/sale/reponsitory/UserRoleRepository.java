package com.example.sale.reponsitory;

import com.example.sale.entity.User;
import com.example.sale.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {
    List<UserRoles> findByUserId(User userId);
}
