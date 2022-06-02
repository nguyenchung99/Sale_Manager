package com.example.sale.reponsitory;

import com.example.sale.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountReponsitory extends JpaRepository<Account, Long> {
}
