package com.example.sale.controller;


import com.example.sale.reponsitory.AuthenticationProvider;
import com.example.sale.request.UserRequest;
import com.example.sale.response.ResponseBase;
import com.example.sale.response.TokenResponse;
import com.example.sale.entity.ERole;
import com.example.sale.entity.Role;
import com.example.sale.entity.User;
import com.example.sale.entity.UserRoles;
import com.example.sale.payload.SignUpRequest;
import com.example.sale.reponsitory.RoleRepository;
import com.example.sale.reponsitory.UserRepository;
import com.example.sale.reponsitory.UserRoleRepository;
import com.example.sale.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    UserRepository userRepository;

    RoleRepository roleRepository;

    UserRoleRepository userRoleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    JwtUtils jwtUtils;

    public AuthController(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }
    @PostMapping("/signUp")
    public ResponseEntity signUp (@RequestBody SignUpRequest signUpRequest){
        UserRoles userRoles = new UserRoles();
        if(userRepository.existsAllByUsername(signUpRequest.getUsername())) return ResponseEntity.badRequest().body("Username existed!");
        if(userRepository.existsAllByEmail(signUpRequest.getEmail())) return  ResponseEntity.badRequest().body("Email existed!");
        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()),signUpRequest.getEmail());
        Set<String> reqRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if(!Objects.nonNull(reqRoles)){
            Role role = roleRepository.findAllByName(ERole.ROLE_USER.name());
            if(Objects.nonNull(role)) roles.add(role);
        }else{
            for (String role: reqRoles){
                switch (role){
                    case "admin":
                        Role adminRole = roleRepository.findAllByName(ERole.ROLE_ADMIN.name());
                        roles.add(adminRole);
                    case "mod":
                        Role modRole = roleRepository.findAllByName(ERole.ROLE_MODERATOR.name());
                        roles.add(modRole);
                    default:
                        Role userRole = roleRepository.findAllByName(ERole.ROLE_USER.name());
                        roles.add(userRole);
                }

            }
        }

        userRepository.save(user);
        userRoles.setUserId(user);
        for(Role r:roles){
            userRoles.setRoleId(r);
            userRoleRepository.save(userRoles);
        }
        return ResponseEntity.ok().body("Register Successfully");
    }

    @PostMapping("/signIn")
    public ResponseBase signIn(@RequestBody UserRequest userRequest) throws Exception {
        User user = authenticationProvider.authenticate(userRequest.username, userRequest.password);
        String token = jwtUtils.generateToken(user);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAcessToken(token);
        return ResponseBase.getSuccess(tokenResponse);
    }

}
