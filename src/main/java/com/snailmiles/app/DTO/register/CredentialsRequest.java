package com.snailmiles.app.DTO.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsRequest {
    private String email;
    private String password;
}
