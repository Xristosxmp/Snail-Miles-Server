package com.snailmiles.app.DTO.logout;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutResponse {
    private int status = 200;
    private String message;
}
