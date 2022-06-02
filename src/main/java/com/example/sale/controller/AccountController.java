package com.example.sale.controller;

import com.example.sale.entity.Account;
import com.example.sale.entity.Order;
import com.example.sale.reponsitory.AccountReponsitory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {
    AccountReponsitory accountReponsitory;
    AccountController(AccountReponsitory accountReponsitory){
        this.accountReponsitory = accountReponsitory;
    }
    @GetMapping("/get")
    List<Account> list(){
        return accountReponsitory.findAll();
    }
    @PostMapping("/post")
    ResponseEntity<Account> create(@RequestBody Account data){
        accountReponsitory.save(data);
        return new ResponseEntity<>(accountReponsitory.save(data), HttpStatus.OK);
    }
    @PutMapping("/put")
    ResponseEntity<Account> update(@RequestBody Long id, Account data){
        Account account = accountReponsitory.getById(id);
        if(account != null){
            data.setId(account.getId());
            return new ResponseEntity<>(accountReponsitory.save(account),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete")
    ResponseEntity<Account> delete(@RequestBody Long id){
        boolean check = accountReponsitory.existsById(id);
        if(check == true){
            accountReponsitory.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
