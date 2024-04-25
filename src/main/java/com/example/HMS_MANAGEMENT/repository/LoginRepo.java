package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepo extends JpaRepository<LoginEntity,Long> {
    LoginEntity findByUserId(String userId);
}
