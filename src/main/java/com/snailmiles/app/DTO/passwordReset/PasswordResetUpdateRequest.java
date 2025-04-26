package com.snailmiles.app.DTO.passwordReset;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetUpdateRequest {
    private String email;
    private String password;
}


