package com.user.user.controller;

import com.user.user.model.Role;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String  lastname;
    private String email;
    private String password;
    private Role role;

}
