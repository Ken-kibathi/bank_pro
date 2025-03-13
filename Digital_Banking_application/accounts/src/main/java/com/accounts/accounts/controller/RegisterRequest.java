package com.accounts.accounts.controller;

import com.accounts.accounts.model.Role;
import lombok.*;

import java.util.Date;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String fullName;
    private String  phoneNumber;
    private String address;
    private Date createdAt;
    private String email;
    private String password;
    private Role role;

}

