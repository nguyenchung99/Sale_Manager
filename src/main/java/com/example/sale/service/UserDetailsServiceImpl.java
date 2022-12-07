package com.example.sale.service;

import antlr.build.ANTLR;
import com.example.sale.entity.Role;
import com.example.sale.entity.User;
import com.example.sale.entity.UserRoles;
import com.example.sale.reponsitory.UserRepository;
import com.example.sale.reponsitory.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  UserRoleRepository userRoleRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findAllByUsername(username);
    List<Role> roleList = new ArrayList<>();
    if(!Objects.nonNull(user)) throw new UsernameNotFoundException("Not found User");
    else {
      List<UserRoles> userRoles = userRoleRepository.findByUserId(user);
      for(UserRoles userR : userRoles){
        roleList.add(userR.getRoleId());
      }
    }
    return UserDetailsImpl.build(user, roleList);
  }

}
