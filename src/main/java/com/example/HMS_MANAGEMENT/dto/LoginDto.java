package com.example.HMS_MANAGEMENT.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
public class LoginDto {

    private Long id;

    private String userName;

    private String userId;

    private String password;

    private String role;
}
