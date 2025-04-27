package com.snailmiles.app.DTO.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterBodyResponse {
    private int status = 200;
    private String message;
}
