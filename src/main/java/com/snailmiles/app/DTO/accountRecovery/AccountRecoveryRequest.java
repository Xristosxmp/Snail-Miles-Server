package com.snailmiles.app.DTO.accountRecovery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRecoveryRequest {
    private String email;
    private String password;
}
