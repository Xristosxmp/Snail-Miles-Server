package com.snailmiles.app.DTO.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBadResponse {
    private int status = 400;
    private String message = "Δεν βρέθηκε λογαριασμός στην βάση δεδομένων μας";
}
