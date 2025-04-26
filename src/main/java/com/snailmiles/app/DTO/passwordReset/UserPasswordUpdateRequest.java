package com.snailmiles.app.DTO.passwordReset;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordUpdateRequest {
    private String email;
    private String currentPassword;
    private String newPassword;
}
