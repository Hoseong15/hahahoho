package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.entity.LoginEntity;
import com.example.HMS_MANAGEMENT.repository.LoginRepo;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.security.core.userdetails.UserDetailsService;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService  {

    @Autowired
    private LoginRepo loginRepo;

    public LoginEntity findByUserId(String userId){
        return loginRepo.findByUserId(userId);
    }

    public boolean authenticate(String userId, String password){
        LoginEntity user = loginRepo.findByUserId(userId);
        return user != null && user.getPassword().equals(password);
    }


}
